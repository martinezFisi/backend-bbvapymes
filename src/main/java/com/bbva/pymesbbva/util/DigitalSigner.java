package com.bbva.pymesbbva.util;

import com.bbva.pymesbbva.client.AmazonS3Client;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.PrivateKey;

@Slf4j
@Component
public class DigitalSigner {

    private final AmazonS3Client amazonS3Client;
    private final PropertiesUtil propertiesUtil;

    public DigitalSigner(AmazonS3Client amazonS3Client, PropertiesUtil propertiesUtil) {
        this.amazonS3Client = amazonS3Client;
        this.propertiesUtil = propertiesUtil;
    }

    public String sign(String pdfName) {
        try{
            var pdfDirectory = propertiesUtil.AMAZON_S3_URL.concat(propertiesUtil.PDFS_DIRECTORY);
            var certificateDirectory = propertiesUtil.AMAZON_S3_URL.concat(propertiesUtil.DIGITAL_SIGN_CERTIFICATE);
            var certificatePassword = propertiesUtil.DIGITAL_SIGN_CERTIFICATE_PASSWORD;

            var keyStore = KeyStore.getInstance("pkcs12");

            var httpURLConnection = (HttpURLConnection)new URL(certificateDirectory).openConnection();
            keyStore.load(httpURLConnection.getInputStream(), certificatePassword.toCharArray());

            var alias = keyStore.aliases().nextElement();

            var privateKey = (PrivateKey) keyStore.getKey(alias, certificatePassword.toCharArray());
            var certificateChain = keyStore.getCertificateChain(alias);

            var pdfReader = new PdfReader(new URL(pdfDirectory.concat(pdfName)).openStream());
            var signedPDFName = pdfName.replace(".pdf", "").concat("-signed.pdf");
            var fileOutputStream = new FileOutputStream(signedPDFName);

            var pdfStamper = PdfStamper.createSignature(pdfReader, fileOutputStream, '?');

            var signatureAppearance = pdfStamper.getSignatureAppearance();
            signatureAppearance.setCrypto(privateKey, certificateChain, null, PdfSignatureAppearance.WINCER_SIGNED);

            signatureAppearance.setReason("Firma PKCS12");
            signatureAppearance.setLocation("HACKATON BBVA, LIMA PERU");

            signatureAppearance.setVisibleSignature(new com.itextpdf.text.Rectangle(200, 340, 30, 20), 1, null);
            pdfStamper.close();

            var inputStream = new FileInputStream(signedPDFName);
            return amazonS3Client.uploadFile(propertiesUtil.PDFS_SIGNED_DIRECTORY.concat(signedPDFName), inputStream);
        } catch(Exception e) {
            log.error(e.getMessage(), e);
            return e.getMessage();
        }
    }

}

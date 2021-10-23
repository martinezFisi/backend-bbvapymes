package com.bbva.pymesbbva.util;

import com.bbva.pymesbbva.client.AmazonS3Client;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class PDFGenerator {

    private final AmazonS3Client amazonS3Client;
    private final PropertiesUtil propertiesUtil;

    public PDFGenerator(AmazonS3Client amazonS3Client, PropertiesUtil propertiesUtil) {
        this.amazonS3Client = amazonS3Client;
        this.propertiesUtil = propertiesUtil;
    }

    public String generatePDF(String text) {
        try {
            var document = new Document(PageSize.A4, 50, 50, 50, 50);
            var pdfName = "bbva-".concat(UUID.randomUUID().toString()).concat(".pdf");
            log.info("PDF Name: {}", pdfName);

            var fileOutputStream = new FileOutputStream(pdfName);
            var pdfWriter = PdfWriter.getInstance(document, fileOutputStream);
            pdfWriter.setInitialLeading(5);

            document.open();
            document.add(new Paragraph(text));
            document.close();

            var inputStream = new FileInputStream(pdfName);
            return amazonS3Client.uploadFile(propertiesUtil.PDFS_DIRECTORY.concat(pdfName), inputStream);
        } catch (DocumentException | IOException e) {
            log.error(e.getMessage(), e);
            return e.getMessage();
        }
    }
}

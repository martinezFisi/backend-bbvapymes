package com.bbva.pymesbbva.util;

import com.bbva.pymesbbva.client.AmazonS3Client;
import com.bbva.pymesbbva.dto.PDFGeneratedDTO;
import com.bbva.pymesbbva.dto.SolicitudDTO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
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

    public PDFGeneratedDTO generatePDF(SolicitudDTO solicitudDTO) {
        try {
            var document = new Document(PageSize.A4, 50, 50, 50, 50);
            var pdfName = "bbva-".concat(UUID.randomUUID().toString()).concat(".pdf");
            log.info("PDF Name: {}", pdfName);

            var fileOutputStream = new FileOutputStream(pdfName);
            var pdfWriter = PdfWriter.getInstance(document, fileOutputStream);
            pdfWriter.setInitialLeading(5);

            document.open();

            var bbvaLogo = Image.getInstance(propertiesUtil.AMAZON_S3_URL.concat(propertiesUtil.AMAZON_S3_UTILS_BBVA_LOGO));
            bbvaLogo.scaleToFit(150, 150);
            bbvaLogo.setAlignment(Chunk.ALIGN_CENTER);
            document.add(bbvaLogo);

            var p1 = new Paragraph("HOJA RESUMEN", FontFactory.getFont("arial", 15, Font.BOLD, BaseColor.BLACK));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Tipo de Servicio: ", FontFactory.getFont("arial", 11, Font.BOLD, BaseColor.BLACK)));
            document.add(new Paragraph(solicitudDTO.getTipoServicio(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));

            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Producto: ", FontFactory.getFont("arial", 11, Font.BOLD, BaseColor.BLACK)));
            document.add(new Paragraph(solicitudDTO.getProducto(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));

            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Datos de Empresa Solicitante: ", FontFactory.getFont("arial", 11, Font.BOLD, BaseColor.BLACK)));
            document.add(Chunk.NEWLINE);

            var pdfPTable = new PdfPTable(3);
            pdfPTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            pdfPTable.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfPTable.getDefaultCell().setFixedHeight(25f);
            pdfPTable.setTotalWidth(450);
            pdfPTable.setWidths(new int[]{150, 150, 150});
            pdfPTable.setLockedWidth(true);

            pdfPTable.addCell(new Paragraph("RUC", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            pdfPTable.addCell(new Paragraph("Nombre Comercial", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            pdfPTable.addCell(new Paragraph("Rubro", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));

            pdfPTable.addCell(new Paragraph(solicitudDTO.getRuc(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));
            pdfPTable.addCell(new Paragraph(solicitudDTO.getClienteEmpresaRazonSocial(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));
            pdfPTable.addCell(new Paragraph(solicitudDTO.getClienteEmpresaRubro(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));

            pdfPTable.addCell(new Paragraph("Inicio de Actividades", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            pdfPTable.addCell(new Paragraph("Estado", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            pdfPTable.addCell(new Paragraph("Dirección fiscal", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));

            pdfPTable.addCell(new Paragraph(solicitudDTO.getClienteEmpresaFechaInicioActividades(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));
            pdfPTable.addCell(new Paragraph(solicitudDTO.getClienteEmpresaEstado(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));
            pdfPTable.addCell(new Paragraph(solicitudDTO.getClienteEmpresaDireccionFiscal(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));

            pdfPTable.addCell(new Paragraph("Nivel de Calificación", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            pdfPTable.addCell(new Paragraph("Gerente General", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            pdfPTable.addCell(new Paragraph("Gerente Administrativo", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));

            pdfPTable.addCell(new Paragraph(solicitudDTO.getClienteEmpresaNivelCalificacion(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));
            pdfPTable.addCell(new Paragraph(solicitudDTO.getClienteEmpresaGerenteGeneral(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));
            pdfPTable.addCell(new Paragraph(solicitudDTO.getClienteEmpresaGerenteAdministrativo(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));

            document.add(pdfPTable);

            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Datos del representante Legal: ", FontFactory.getFont("arial", 11, Font.BOLD, BaseColor.BLACK)));
            document.add(Chunk.NEWLINE);

            var pdfPTable2 = new PdfPTable(3);
            pdfPTable2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            pdfPTable2.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfPTable2.getDefaultCell().setFixedHeight(25f);
            pdfPTable2.setTotalWidth(450);
            pdfPTable2.setWidths(new int[]{150, 150, 150});
            pdfPTable2.setLockedWidth(true);

            pdfPTable2.addCell(new Paragraph("DNI", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            pdfPTable2.addCell(new Paragraph("Nombres", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            pdfPTable2.addCell(new Paragraph("Apellidos", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));

            pdfPTable2.addCell(new Paragraph(solicitudDTO.getDni(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));
            pdfPTable2.addCell(new Paragraph(solicitudDTO.getRepresentanteLegalNombres(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));
            pdfPTable2.addCell(new Paragraph(solicitudDTO.getRepresentanteLegalApellidos(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));

            pdfPTable2.addCell(new Paragraph("Estado Civil", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            pdfPTable2.addCell(new Paragraph("Edad", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            pdfPTable2.addCell(new Paragraph("Sexo", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));

            pdfPTable2.addCell(new Paragraph(solicitudDTO.getRepresentanteLegalEstadoCivil(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));
            pdfPTable2.addCell(new Paragraph(solicitudDTO.getRepresentanteLegalEdad() + " años", FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));
            pdfPTable2.addCell(new Paragraph(solicitudDTO.getRepresentanteLegalSexo(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));

            pdfPTable2.addCell(new Paragraph("Tipo", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            pdfPTable2.addCell(new Paragraph("Poderdante", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            pdfPTable2.addCell(new Paragraph("Estado", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));

            pdfPTable2.addCell(new Paragraph(solicitudDTO.getRepresentanteLegalTipo(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));
            pdfPTable2.addCell(new Paragraph(solicitudDTO.getCartaPoderPoderdante(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));
            pdfPTable2.addCell(new Paragraph(solicitudDTO.getCartaPoderEstado(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));

            document.add(pdfPTable2);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            var p2 = new Paragraph("------------------------------------\n" + solicitudDTO.getClienteEmpresaRazonSocial()
                    + "   \n" + solicitudDTO.getRepresentanteLegalApellidos() + "      ", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK));
            p2.setAlignment(Element.ALIGN_RIGHT);
            document.add(p2);

            document.close();

            var inputStream = new FileInputStream(pdfName);
            amazonS3Client.uploadFile(propertiesUtil.PDFS_DIRECTORY.concat(pdfName), inputStream);

            return PDFGeneratedDTO.builder()
                                  .pdfURL(propertiesUtil.AMAZON_S3_URL.concat(propertiesUtil.PDFS_DIRECTORY).concat(pdfName))
                                  .pdfName(pdfName)
                                  .message("success")
                                  .build();
        } catch (DocumentException | IOException e) {
            log.error(e.getMessage(), e);
            return PDFGeneratedDTO.builder().message(e.getMessage()).build();
        }
    }
}

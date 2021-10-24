package com.bbva.pymesbbva.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PDFGeneratedDTO {

    private String pdfURL;
    private String pdfName;
    private String message;

}

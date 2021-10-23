package com.bbva.pymesbbva.dto.infobip.sms;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MessageSmsInfobipDto {

    private String from;
    private List<DestinationSmsInfobipDto> destinations;
    private String text;

}

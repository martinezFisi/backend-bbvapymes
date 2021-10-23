package com.bbva.pymesbbva.dto.infobip.sms;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RequestSmsInfobipDto {

    private List<MessageSmsInfobipDto> messages;

}

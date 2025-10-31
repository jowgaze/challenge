package com.challenge.dto.channel;

import com.challenge.domain.model.Channel;
import org.modelmapper.ModelMapper;

public class ChannelMapper {
    public static ChannelResponseDto toDto(Channel channel){
        return new ModelMapper().map(channel, ChannelResponseDto.class);
    }
}

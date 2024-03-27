package com.backend.Backend_supermarket.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
        @JsonProperty("message")
        private String message;
}

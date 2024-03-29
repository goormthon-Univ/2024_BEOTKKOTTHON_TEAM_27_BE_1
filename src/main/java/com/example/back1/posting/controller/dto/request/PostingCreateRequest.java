package com.example.back1.posting.controller.dto.request;

import com.example.back1.posting.domain.Posting;
import com.example.back1.store.domain.Store;

import java.util.List;

public record PostingCreateRequest(
        Long userId,
        Long storeId,
        String postingType,
        String postingChannel,
        List<String> targetAge,
        List<String> targetGender,
        String promotionType,
        String promotionSubject,
        String promotionContent,
        String fileName
) {
    public Posting toPosting(Store store) {
        return Posting.createPosting(
                store, postingType, postingChannel, String.join(" ,", targetAge), String.join(" ,", targetGender), promotionType, promotionSubject, promotionContent, fileName
        );
    }
}

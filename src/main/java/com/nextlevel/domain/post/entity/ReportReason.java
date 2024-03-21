package com.nextlevel.domain.post.entity;

import lombok.Getter;

@Getter
public enum ReportReason {

    SEXUAL_CONTENT("선정적이에요."),
    VIOLENT_CONTENT("폭력적 또는 혐오적이에요."),
    INADEQUATE_CONTENT("부정적이에요."),
    SPAM_CONTENT("스팸이에요."),
    ILLEGAL_INFORMATION_CONTENT("불법정보가 포함되어 있어요.");

    private final String reason;

    ReportReason(String reason) {
        this.reason = reason;
    }
}

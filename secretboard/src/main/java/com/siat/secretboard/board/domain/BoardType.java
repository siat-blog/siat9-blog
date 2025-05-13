package com.siat.secretboard.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardType {
    DRAGON(1L),
    ELF(2L),
    HOBBIT(3L);

    private final Long value;

    // 문자열 → 숫자 변환 메서드
    public static Long toNumber(String name) {
        for (BoardType type : BoardType.values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type.getValue();
            }
        }
        throw new IllegalArgumentException("Unknown BoardType: " + name);
    }
    // 숫자(Long) → 문자열(Enum 이름) 변환 메서드
    public static String toString(Long value) {
        for (BoardType type : BoardType.values()) {
            if (type.getValue().equals(value)) {
                return type.name();
            }
        }
        throw new IllegalArgumentException("Unknown BoardType value: " + value);
    }
}

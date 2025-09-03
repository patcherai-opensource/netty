/*
 * Copyright 2016 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.handler.codec.smtp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class SmtpUtils {

    static List<CharSequence> toUnmodifiableList(CharSequence... sequences) {
        if (sequences == null || sequences.length == 0) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(Arrays.asList(sequences));
    }

    /**
     * Validates SMTP parameters to prevent SMTP command injection.
     * 
     * @param parameters the parameters to validate
     * @throws IllegalArgumentException if any parameter contains CR or LF characters
     */
    static void validateSmtpParameters(CharSequence... parameters) {
        if (parameters == null) {
            return;
        }
        for (CharSequence parameter : parameters) {
            if (parameter != null) {
                for (int i = 0; i < parameter.length(); i++) {
                    char c = parameter.charAt(i);
                    if (c == '\r' || c == '\n') {
                        throw new IllegalArgumentException("SMTP parameter contains illegal character: " + 
                                                         (c == '\r' ? "CR" : "LF"));
                    }
                }
            }
        }
    }

    private SmtpUtils() { }
}

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
        validateSmtpParameters(sequences);
        return Collections.unmodifiableList(Arrays.asList(sequences));
    }

    static List<CharSequence> toUnmodifiableList(List<CharSequence> sequences) {
        if (sequences == null || sequences.isEmpty()) {
            return Collections.emptyList();
        }
        validateSmtpParameters(sequences);
        return Collections.unmodifiableList(sequences);
    }

    static void validateSmtpParameters(CharSequence... parameters) {
        if (parameters == null) {
            return;
        }
        for (CharSequence parameter : parameters) {
            validateSmtpParameter(parameter);
        }
    }

    static void validateSmtpParameters(List<CharSequence> parameters) {
        if (parameters == null) {
            return;
        }
        for (CharSequence parameter : parameters) {
            validateSmtpParameter(parameter);
        }
    }

    private static void validateSmtpParameter(CharSequence parameter) {
        if (parameter == null) {
            return;
        }
        int length = parameter.length();
        for (int i = 0; i < length; i++) {
            char c = parameter.charAt(i);
            if (c == '\r' || c == '\n') {
                throw new IllegalArgumentException("SMTP parameter contains invalid characters (CR/LF): " + parameter);
            }
        }
    }

    private SmtpUtils() { }
}

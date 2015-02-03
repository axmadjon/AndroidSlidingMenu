/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uz.greenwhite.slidingmenu.support.v10.json.android_json;

/**
 * A structure, name or value type in a JSON-encoded string.
 */
public enum JsonToken {

    BEGIN_ARRAY,

    END_ARRAY,

    BEGIN_OBJECT,

    END_OBJECT,

    NAME,

    /**
     * A JSON string.
     */
    STRING,

    /**
     * A JSON number represented in this API by a Java {@code double}, {@code
     * long}, or {@code int}.
     */
    NUMBER,

    /**
     * A JSON {@code true} or {@code false}.
     */
    BOOLEAN,

    /**
     * A JSON {@code null}.
     */
    NULL,

    END_DOCUMENT
}

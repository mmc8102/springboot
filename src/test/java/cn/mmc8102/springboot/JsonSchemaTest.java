package cn.mmc8102.springboot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.report.LogLevel;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.Optional;

/**
 * @Author: SuZhu
 * @description json schema 校验
 * @Date: 2023/3/9 16:57
 */
public class JsonSchemaTest {
        /**
         * 校验规则
         * {
         *   "type": "object",
         *   "title": "empty object",
         *   "properties": {
         *     "param1": {
         *       "type": "number",
         *       "minimum": 0,
         *       "maximum": 100,
         *       "default": "0"
         *     },
         *     "param2": {
         *       "type": "string",
         *       "default": "enum1",
         *       "enum": [
         *         "enum1",
         *         "enum2",
         *         "enum3"
         *       ],
         *       "enumDesc": "枚举类型，只有三个值enum1、enum2、enum3"
         *     },
         *     "param3": {
         *       "type": "boolean",
         *       "default": true
         *     }
         *   },
         *   "required": [
         *     "param1",
         *     "param2"
         *   ]
         * }
         */
        private String jsonSchemaString ="{\n" +
                "  \"type\": \"object\",\n" +
                "  \"title\": \"empty object\",\n" +
                "  \"properties\": {\n" +
                "    \"param1\": {\n" +
                "      \"type\": \"number\",\n" +
                "      \"minimum\": 0,\n" +
                "      \"maximum\": 100,\n" +
                "      \"default\": \"0\"\n" +
                "    },\n" +
                "    \"param2\": {\n" +
                "      \"type\": \"string\",\n" +
                "      \"default\": \"enum1\",\n" +
                "      \"enum\": [\n" +
                "        \"enum1\",\n" +
                "        \"enum2\",\n" +
                "        \"enum3\"\n" +
                "      ],\n" +
                "      \"enumDesc\": \"枚举类型，只有三个值enum1、enum2、enum3\"\n" +
                "    },\n" +
                "    \"param3\": {\n" +
                "      \"type\": \"boolean\",\n" +
                "      \"default\": true\n" +
                "    }\n" +
                "  },\n" +
                "  \"required\": [\n" +
                "    \"param1\",\n" +
                "    \"param2\"\n" +
                "  ]\n" +
                "}";

        @Test
        public void test(){
            /**
             * {
             *   "param1": 37.69437890136598,
             *   "param2": "enum1",
             *   "param3": true
             * }
             */
            String json = "{\"param1\": 137.69437890136598,\"param2\": \"enum4\", \"param3\": true}";
            Pair<Boolean, String> valid = isValid(JSON.parseObject(json));
            System.out.println();
        }

        /**
         * 校验方法
         *
         * @param value   待校验的json
         */
        public Pair<Boolean, String> isValid(JSONObject value) {
            try {
                String message = Optional.ofNullable(validJson(value, jsonSchemaString)).orElse("");
                if (StringUtils.isEmpty(message)) {
                    return ImmutablePair.of(true, StringUtils.EMPTY);
                } else {
                    return ImmutablePair.of(false, message);
                }
            } catch (Exception e) {
                throw new RuntimeException("json校验数据异常");
            }
        }

        /**
         * 使用开源工具https://github.com/java-json-tools/json-schema-validator校验json
         *
         * @param object     被校验对象
         * @param validParam 校验规则
         * @return 返回失败消息，为空则校验成功
         * @throws Exception
         */
        public String validJson(JSONObject object, String validParam) throws Exception {
            JsonNode schema = JsonLoader.fromString(validParam);
            JsonNode data = JsonLoader.fromString(object.toJSONString());
            final JsonSchema jsonSchema = JsonSchemaFactory.byDefault().getJsonSchema(schema);
            ProcessingReport report = jsonSchema.validate(data);
            StringBuilder sBuilder = new StringBuilder();
            report.forEach(pm -> {
                if (LogLevel.ERROR.equals(pm.getLogLevel())) {
                    JsonNode jsonNode = pm.asJson();
                    JsonNode key = Optional.ofNullable(jsonNode)
                            .map(o -> o.get("instance"))
                            .map(r -> r.get("pointer"))
                            .orElse(null);
                    sBuilder.append("errKey: ")
                            .append(key == null ? "" : key.asText())
                            .append(" errMsg: ")
                            .append(pm.getMessage())
                            .append(" ");
                }
            });
            return sBuilder.toString();
        }

}

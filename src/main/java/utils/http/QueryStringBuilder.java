package utils.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * giuseppe.adaldo
 */
public class QueryStringBuilder {

    private static final String PARAMETER_TEMPLATE = "%s=%s&";

    private static final String DEFAULT_ENCODING = "UTF-8";

    public static String buildURIParams(String paramNames[], Object... params) throws Exception {
        if (paramNames == null || paramNames.length == 0 || params == null || paramNames.length != params.length)
            return "";

        final StringBuilder paramBuilder = new StringBuilder(paramNames.length * 36);
        paramBuilder.append("?");
        for (int i = 0; i < paramNames.length; i++) {
            if (paramNames[i] != null && !paramNames[i].isEmpty() && params[i] != null) {
                if (params[i] instanceof Object[]) {
                    final Object[] arrayParam = (Object[]) params[i];
                    for (Object anArrayParam : arrayParam)
                        paramBuilder.append(queryParameterAppend(paramNames[i], anArrayParam));
                } else {
                    paramBuilder.append(queryParameterAppend(paramNames[i], params[i]));
                }
            }
        }
        final String stringToReturn = paramBuilder.toString();
        return stringToReturn.substring(0, stringToReturn.length() - 1);
    }

    public static String queryParameterAppend(String paramName, Object val) throws UnsupportedEncodingException {
        return String.format(
                PARAMETER_TEMPLATE,
                URLEncoder.encode(paramName, DEFAULT_ENCODING),
                URLEncoder.encode(String.valueOf(val), DEFAULT_ENCODING));
    }
}

package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        List<MethodSignature.Argument> methodArgumentsList = parseMethodArgs(signatureString);

        String methodAccessModifier, methodReturnType, methodName;

        String[] startArray = signatureString.split("\\b\\(.*");
        String[] methodSignatureArr = startArray[0].split(" ");
        if (methodSignatureArr.length < 3) {
            methodAccessModifier = null;
        } else {
            methodAccessModifier = methodSignatureArr[0];
        }
        methodReturnType = methodSignatureArr[methodSignatureArr.length - 2];
        methodName = methodSignatureArr[methodSignatureArr.length - 1];

        MethodSignature methodSignature = new MethodSignature(methodName, methodArgumentsList);
        methodSignature.setAccessModifier(methodAccessModifier);
        methodSignature.setReturnType(methodReturnType);
        return methodSignature;
    }

    public List<MethodSignature.Argument> parseMethodArgs(String signatureString) {
        List<MethodSignature.Argument> methodArgumentsList = new ArrayList<>();
        int methodArgsStartIndex = 0;
        int methodArgsEndIndex = signatureString.length();

        for (int i = 0; i < signatureString.length(); i++) {
            if (signatureString.charAt(i) == '(') {
                methodArgsStartIndex = i + 1;
            } else if (signatureString.charAt(i) == ')') {
                methodArgsEndIndex = i;
            }
        }
        if (methodArgsStartIndex != methodArgsEndIndex) {
            String[] methodArgs = signatureString.substring(methodArgsStartIndex, methodArgsEndIndex).split(", ");
            for (String methodArg : methodArgs) {
                String[] arg = methodArg.split(" ");
                MethodSignature.Argument argument = new MethodSignature.Argument(arg[0], arg[1]);
                methodArgumentsList.add(argument);
            }
        }
        return methodArgumentsList;
    }
}

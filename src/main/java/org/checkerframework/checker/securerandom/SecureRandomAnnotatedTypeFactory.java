package org.checkerframework.checker.securerandom;

import org.checkerframework.common.basetype.BaseAnnotatedTypeFactory;
import org.checkerframework.common.basetype.BaseTypeChecker;

public class SecureRandomAnnotatedTypeFactory extends BaseAnnotatedTypeFactory {

    public SecureRandomAnnotatedTypeFactory(BaseTypeChecker checker) {
        super(checker);
        this.postInit();
    }
}

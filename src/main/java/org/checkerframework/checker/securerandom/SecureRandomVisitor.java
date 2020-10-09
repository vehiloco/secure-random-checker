package org.checkerframework.checker.securerandom;

import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.MethodInvocationTree;

import org.checkerframework.common.basetype.BaseTypeChecker;
import org.checkerframework.common.basetype.BaseTypeVisitor;
import org.checkerframework.javacutil.TreeUtils;

import java.security.SecureRandom;
import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;

public class SecureRandomVisitor extends BaseTypeVisitor<SecureRandomAnnotatedTypeFactory> {

    final ProcessingEnvironment env;

    public SecureRandomVisitor(BaseTypeChecker checker) {
        super(checker);
        env = checker.getProcessingEnvironment();
    }

    @Override
    public Void visitNewClass(com.sun.source.tree.NewClassTree node, Void p) {
        ExpressionTree identifier = node.getIdentifier();
        if (identifier.toString().equals("Random")) {
            checker.reportError(node, "insecure.random.use");
        }
        return super.visitNewClass(node, p);
    }

    @Override
    public Void visitMethodInvocation(MethodInvocationTree node, Void p) {
        List<ExecutableElement> setSeed = TreeUtils.getMethods(
            SecureRandom.class.getName(),
            "setSeed",
            1,
            env);
        if (TreeUtils.isMethodInvocation(node, setSeed, env)) {
//            ExpressionTree valueExp = node.getArguments().get(0);
            checker.reportError(node, "set.constant.seed");
        }
        return super.visitMethodInvocation(node, p);
    }
}

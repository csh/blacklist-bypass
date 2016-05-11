package xyz.smirking.blacklist.adapter;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodAdapter extends MethodVisitor implements Opcodes {
    private final MethodVisitor visitor;

    public MethodAdapter(MethodVisitor visitor) {
        super(ASM5);
        this.visitor = visitor;
    }

    @Override
    public void visitCode() {
        visitor.visitInsn(ICONST_0);
        visitor.visitInsn(IRETURN);
        visitor.visitMaxs(1, 2);
    }
}

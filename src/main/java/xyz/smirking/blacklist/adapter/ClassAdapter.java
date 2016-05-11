package xyz.smirking.blacklist.adapter;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassAdapter extends ClassVisitor implements Opcodes {
    public ClassAdapter(ClassVisitor visitor) {
        super(ASM5, visitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor visitor = cv.visitMethod(access, name, desc, signature, exceptions);
        if (visitor == null) {
            return null;
        }
        return name.equals("isBlockedServer") ? new MethodAdapter(visitor) : visitor;
    }
}

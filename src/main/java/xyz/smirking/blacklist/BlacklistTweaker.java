package xyz.smirking.blacklist;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraft.launchwrapper.LogWrapper;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import xyz.smirking.blacklist.adapter.ClassAdapter;

public class BlacklistTweaker implements ITweaker, IClassTransformer, Opcodes {
    private List<String> args;

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        this.args = new ArrayList<>(args);
        this.args.add(gameDir.getAbsolutePath());
        this.args.add("--assetsDir");
        this.args.add(assetsDir.getAbsolutePath());
        this.args.add("--version");
        this.args.add(profile);
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        classLoader.registerTransformer(getClass().getCanonicalName());
    }

    @Override
    public String getLaunchTarget() {
        return "net.minecraft.client.main.Main";
    }

    @Override
    public String[] getLaunchArguments() {
        return args.toArray(new String[args.size()]);
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (name.equals("io.netty.bootstrap.Bootstrap")) {
            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            ClassReader reader = new ClassReader(basicClass);
            ClassAdapter adapter = new ClassAdapter(writer);
            reader.accept(adapter, 0);
            LogWrapper.info("You should now be able to join blacklisted servers.");
            return writer.toByteArray();
        }
        return basicClass;
    }
}

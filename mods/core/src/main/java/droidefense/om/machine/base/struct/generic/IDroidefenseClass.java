package droidefense.om.machine.base.struct.generic;

import java.util.Hashtable;

/**
 * Created by sergio on 25/3/16.
 */
public abstract class IDroidefenseClass {

    public abstract String toString();

    public abstract IAtomMethod getVirtualMethod(final String name, final String descriptor, boolean getRealMethod);

    public abstract IAtomMethod getDirectMethod(final String name, final String descriptor, boolean getRealMethod);

    public abstract  IAtomField getStaticField(final String name);

    //GETTERS AND SETTERS

    public abstract String getName();

    public abstract void setName(String name);

    public abstract int getFlag();

    public abstract void setFlag(int flag);

    public abstract boolean isInterface();

    public abstract void setInterface(boolean anInterface);

    public abstract String getSuperClass();

    public abstract void setSuperClass(String superClass);

    public abstract String[] getInterfaces();

    public abstract void setInterfaces(String[] interfaces);

    public abstract IAtomField[] getInstanceFields();

    public abstract void setInstanceFields(IAtomField[] instanceFields);

    public abstract IAtomField[] getStaticFields();

    public abstract void setStaticFields(IAtomField[] staticFields);

    public abstract Hashtable getStaticFieldMap();

    public abstract void setStaticFieldMap(Hashtable staticFieldMap);

    public abstract IAtomMethod[] getDirectMethods();

    public abstract void setDirectMethods(IAtomMethod[] directMethods);

    public abstract IAtomMethod[] getVirtualMethods();

    public abstract void setVirtualMethods(IAtomMethod[] virtualMethods);

    public abstract boolean isBinded();

    public abstract void setBinded(boolean binded);

    public abstract IAtomMethod getMethod(String name, String descriptor, boolean getRealMethod);

    public abstract IAtomMethod[] getMethod(String name);

    public abstract IAtomField getField(String fieldName, String fieldType);

    public abstract boolean isFake();

    public abstract IAtomMethod[] getAllMethods();

    public abstract void addMethod(IAtomMethod methodToCall);

    public String getAndroifiedClassName(){
        String name = getName();
        int idx = name.lastIndexOf("/");
        if (idx != -1)
            name = name.substring(0, idx).replace("/", ".");
        return name;
    }

    public boolean isAnnotationClass() {
        String className = getAndroifiedClassName();
        return className.startsWith("android.annotation");
    }

    public boolean isAndroidv4v7Class() {
        String className = getAndroifiedClassName();
        className = cleanClassName(className);
        return className.startsWith("android.support.v4")
                || className.startsWith("android.support.v7")
                || className.startsWith("android.support.v13")
                || className.startsWith("android.support.v14")
                || className.startsWith("android.support.v17")
                || className.startsWith("android.support.graphics")
                || className.startsWith("android.support.design")
                || className.startsWith("android.support.customtabs")
                || className.startsWith("android.support.annotation");
    }

    public boolean isAndroidRclass() {
        String className = getName();
        return className.endsWith("R$attr")
                || className.endsWith("R")
                || className.endsWith("R$drawable")
                || className.endsWith("R$dimen")
                || className.endsWith("R$integer")
                || className.endsWith("R$mipmap")
                || className.endsWith("R$styleable")
                || className.endsWith("R$id")
                || className.endsWith("R$style")
                || className.endsWith("R$bool")
                || className.endsWith("R$color")
                || className.endsWith("R$anim")
                || className.endsWith("R$string")
                || className.endsWith("R$xml")
                || className.endsWith("R$menu")
                || className.endsWith("R$layout");
    }

    public boolean isAndroidUIRelatedClass() {
        String className = getAndroifiedClassName();
        return className.equals("android.widget.TextView")
                || className.equals("android.app.Activity");
    }

    public String cleanClassName(String name) {
        name = name.replace("/", ".");
        int idx = name.indexOf("$");
        if (idx != -1)
            name = name.substring(0, idx);
        return name;
    }

    public static String[] getAndroidRClasses() {
        return new String[]{
                ".R$anim",
                ".R$attr",
                ".R$integer",
                ".R$styleable",
                ".R$layout",
                ".R$mipmap",
                ".R$style",
                ".R",
                ".R$string",
                ".R$drawable",
                ".R$color",
                ".R$dimen",
                ".R$id",
                ".BuildConfig",
                ".R$bool"
        };
    }

    public boolean isDeveloperClass() {
        return !isAndroidUIRelatedClass()
                && !isAnnotationClass()
                && !isAndroidRclass()
                && !isAndroidv4v7Class();
    }
}
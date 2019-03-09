package com.example.zhh_compiler.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * @author brett-zhu
 * created at 2019/3/6 20:34
 */
public final class EntryVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {
    private Filer mFiler = null;
    private TypeMirror mTypeMirror = null;
    private String mPackageName = null;

    public void setFiler(Filer filer) {
        this.mFiler = filer;
    }

    @Override
    public Void visitString(String s, Void aVoid) {
        mPackageName = s;
        return aVoid;
    }

    @Override
    public Void visitType(TypeMirror typeMirror, Void aVoid) {
        this.mTypeMirror = typeMirror;
        generateJavaCode(typeMirror);
        return aVoid;
    }

    private void generateJavaCode(TypeMirror typeMirror) {
        final TypeSpec typeSpec = TypeSpec.classBuilder("WXEntryActivity")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .superclass(TypeName.get(mTypeMirror))
                .build();
        try {
            JavaFile javaFile = JavaFile.builder(mPackageName + ".wxapi", typeSpec).
                    addFileComment("微信入口文件")
                    .build();
            javaFile.writeTo(mFiler);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败了");
        }
    }
}

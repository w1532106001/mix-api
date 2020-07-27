package com.whc.mix_api.generator.plugin;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.mybatis.generator.internal.util.JavaBeansUtil.getGetterMethodName;
import static org.mybatis.generator.internal.util.JavaBeansUtil.getSetterMethodName;
import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 * 生成DTO对象，并且添加Validation验证注解
 *
 * @author bianxinhuan
 */
public class DtoPlugin extends PluginAdapter {

    private String targetPackage;
    private String targetProject;
    private boolean addAuthor;
    private List<String> ignoreColumns;

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        targetPackage = properties.getProperty("targetPackage");
        targetProject = properties.getProperty("targetProject");
        addAuthor = ("true".equals(properties.getProperty("addAuthor", "true")));

        String ignoreColumnsString = properties.getProperty("ignoreColumns");
        if (ignoreColumnsString != null) {
            ignoreColumns = Arrays.asList(ignoreColumnsString.split(","));
        }
    }

    @Override
    public void setContext(Context context) {
        super.setContext(context);
    }

    /**
     * 如果返回true，则插件中的其他方法将不被调用
     *
     * @param warnings
     * @return
     */
    @Override
    public boolean validate(List<String> warnings) {

        boolean valid = true;

        if (!stringHasValue(properties.getProperty("targetProject"))) {
            warnings.add(getString("ValidationError.18", "DTOPlugin", "targetProject"));
            valid = false;
        }

        if (!stringHasValue(properties.getProperty("targetPackage"))) {
            warnings.add(getString("ValidationError.18", "DTOPlugin", "targetPackage"));
            valid = false;
        }

        return valid;
    }

    /**
     * 生成java文件
     *
     * @param introspectedTable
     * @return
     */
    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {

        List<GeneratedJavaFile> files = new ArrayList<GeneratedJavaFile>();

        // model的完全限定名
        String baseRecordType = introspectedTable.getBaseRecordType();
        // model的包名
        String modelPackage = introspectedTable.getContext().getJavaModelGeneratorConfiguration().getTargetPackage();
        // 获取model的类名
        String modelClassName = baseRecordType.replaceAll(modelPackage + ".", "");

        // 设置dto的完全限定名：
        FullyQualifiedJavaType dtoType = new FullyQualifiedJavaType(targetPackage + "." + modelClassName + "DTO");

        TopLevelClass topLevelClass = new TopLevelClass(dtoType);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * " + introspectedTable.getRemarks());
        topLevelClass.addJavaDocLine(" * ");
        if (addAuthor) {
            topLevelClass.addJavaDocLine(" * @author " + System.getProperties().getProperty("user.name"));
        }
        topLevelClass.addJavaDocLine(" */");

        Plugin plugins = context.getPlugins();

        // 生成字段、getter/setter、Validation注解
        for (IntrospectedColumn column : introspectedTable.getAllColumns()) {

            Field field = getJavaBeansField(column, introspectedTable);

            Method method = getJavaBeansGetter(column, introspectedTable);
            if (plugins.modelGetterMethodGenerated(method, topLevelClass, column, introspectedTable, Plugin.ModelClassType.BASE_RECORD)) {
                topLevelClass.addMethod(method);
            }

            if (!introspectedTable.isImmutable()) {
                method = getJavaBeansSetter(column, introspectedTable);
                if (plugins.modelSetterMethodGenerated(method, topLevelClass, column, introspectedTable, Plugin.ModelClassType.BASE_RECORD)) {
                    topLevelClass.addMethod(method);
                }
            }
            // 添加Validation注解
            addFieldValidationAnnotation(field, topLevelClass, column, introspectedTable);
            topLevelClass.addField(field);
            topLevelClass.addImportedType(field.getType());
        }
        GeneratedJavaFile file = new GeneratedJavaFile(topLevelClass, targetProject, context.getJavaFormatter());
        files.add(file);

        return files;
    }

    private Method getJavaBeansGetter(IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType fqjt = introspectedColumn
                .getFullyQualifiedJavaType();
        String property = introspectedColumn.getJavaProperty();

        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(fqjt);
        method.setName(getGetterMethodName(property, fqjt));
        context.getCommentGenerator().addGetterComment(method,
                introspectedTable, introspectedColumn);

        StringBuilder sb = new StringBuilder();
        sb.append("return ");
        sb.append(property);
        sb.append(';');
        method.addBodyLine(sb.toString());

        return method;
    }

    private Field getJavaBeansField(IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType fqjt = introspectedColumn.getFullyQualifiedJavaType();
        String property = introspectedColumn.getJavaProperty();

        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setType(fqjt);
        field.setName(property);

        return field;
    }

    private Method getJavaBeansSetter(IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType fqjt = introspectedColumn
                .getFullyQualifiedJavaType();
        String property = introspectedColumn.getJavaProperty();

        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName(getSetterMethodName(property));
        method.addParameter(new Parameter(fqjt, property));
        context.getCommentGenerator().addSetterComment(method,
                introspectedTable, introspectedColumn);

        StringBuilder sb = new StringBuilder();

        sb.append("this.");
        sb.append(property);
        sb.append(" = ");
        sb.append(property);
        sb.append(';');
        method.addBodyLine(sb.toString());

        return method;
    }

    private void addFieldValidationAnnotation(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable) {

        // 添加注释
        if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
            field.addJavaDocLine("/**");
            StringBuilder sb = new StringBuilder();
            sb.append(" * ");
            sb.append(introspectedColumn.getRemarks());
            field.addJavaDocLine(sb.toString());
            field.addJavaDocLine(" */");
        }

        // 判断当前字段是否为主键
        boolean isPrimaryKey = false;
        for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
            if (introspectedColumn == column) {
                isPrimaryKey = true;
                break;
            }
        }

        boolean isNullable = introspectedColumn.isNullable();
        String columnRemarks = introspectedColumn.getRemarks();
        String fieldFullyQualifiedName = introspectedColumn.getFullyQualifiedJavaType().getFullyQualifiedName();

        try {
            // 字符类型的列增加长度验证
            if (String.class == Class.forName(fieldFullyQualifiedName)) {
                // 非主键增加@NotBlank
                if (!isNullable && !isPrimaryKey) {
                    topLevelClass.addImportedType("org.hibernate.validator.constraints.NotBlank");
                    field.addAnnotation("@NotBlank(message = \"" + columnRemarks + "不能为空\")");
                }
                topLevelClass.addImportedType("org.hibernate.validator.constraints.Length");
                field.addAnnotation(String.format("@Length(min = 0, max = %d , message = \"%s长度必须在{min}和{max}之间\")",
                        introspectedColumn.getLength(), columnRemarks));
            }
            // 数字类型
            else if (Integer.class == Class.forName(fieldFullyQualifiedName)) {
                if (!isNullable) {
                    topLevelClass.addImportedType("org.hibernate.validator.constraints.NotNull");
                    field.addAnnotation("@NotNull(message = \"" + columnRemarks + "不能为空\")");
                }
                topLevelClass.addImportedType("org.hibernate.validator.constraints.Range");
                field.addAnnotation(String.format("@Range(min = 0, max = %d , message = \"%s大小必须在{min}和{max}之间\")",
                        introspectedColumn.getLength(), columnRemarks));
            } else {
                // 其它类型
                if (!isNullable) {
                    topLevelClass.addImportedType("org.hibernate.validator.constraints.NotNull");
                    field.addAnnotation("@NotNull(message = \"" + columnRemarks + "不能为空\")");
                }
            }
        } catch (ClassNotFoundException ignored) {
        }
    }
}

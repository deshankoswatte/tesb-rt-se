<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
        xmlns:f="http://karaf.apache.org/xmlns/features/v1.2.0"
        xmlns="http://karaf.apache.org/xmlns/features/v1.2.0">

    <xsl:output method="xml" indent="yes"/>

    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*"/>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template> 

    <xsl:template match="/f:features/f:feature[@name='camel-dozer']/f:bundle[text()='mvn:com.github.dozermapper/dozer-core/6.1.0']/text()">
        <xsl:text>mvn:com.github.dozermapper/dozer-core/6.2.0</xsl:text>
    </xsl:template>
    <xsl:template match="/f:features/f:feature[@name='camel-dozer']/f:bundle[text()='mvn:com.github.dozermapper/dozer-schema/6.1.0']/text()">
        <xsl:text>mvn:commons-io/commons-io/2.6</xsl:text>
    </xsl:template>
    <xsl:template match="/f:features/f:feature[@name='camel-dozer']/f:bundle[text()='mvn:javax.el/javax.el-api/2.2.5']/text()">
        <xsl:text>mvn:javax.el/javax.el-api/3.0.0</xsl:text>
    </xsl:template>
    <xsl:template match="/f:features/f:feature[@name='camel-dozer']/f:bundle[text()='mvn:org.glassfish.web/javax.el/2.2.5']/text()">
        <xsl:text>mvn:org.glassfish/javax.el/3.0.0</xsl:text>
    </xsl:template>

    <xsl:template match="/f:features/f:feature[@name='camel-dozer']/f:bundle[text()='mvn:org.glassfish.web/javax.el/2.2.5']">
        <xsl:copy>
            <xsl:apply-templates select="@*"/>
            <xsl:apply-templates/>
        </xsl:copy>
        
        <xsl:element name="bundle" namespace="http://karaf.apache.org/xmlns/features/v1.2.0">
            <xsl:attribute name="dependency">true</xsl:attribute>
            <xsl:text>mvn:org.objenesis/objenesis/2.6</xsl:text>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>

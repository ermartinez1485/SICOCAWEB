<%-- 
    Document   : main
    Created on : Apr 24, 2016, 12:22:07 PM
    Author     : eric.martinez
--%>

<%@page contentType="text/html"  pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Mantenimiento de Areas</title>
        </head>
        <body>
            <!--
            
            <f:loadBundle basename="cr.ac.una.icai.sicocaweb.etiquetas.recursos" var="eti"/>
        
            -->
            <h1><h:outputText value="Mantenimiento Areas"/></h1>
            <h:form id="form1">
                <table>
                    <tr>
                        <td>ID Area</td>
                        <td><h:inputText
                                disabled="#{areaBean.modificando==true}"
                                id="txtIDArea" 
                                required="true" requiredMessage="Requerido"
                                value="#{areaBean.laArea.idArea}"/> 
                                <h:message for="txtIDArea" />
                            </td>
                        </tr>
                        <tr>
                            <td>Nombre</td>
                            <td><h:inputText binding="#{areaBean.inNombre}"
                                    id="txtNombre" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{areaBean.laArea.nombre}" />
                                <h:message for="txtNombre" /></td>
                        </tr>
                        <tr>
                            <td>Ubicacion</td>
                            <td><h:inputText 
                                    id="txtUbicacion" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{areaBean.laArea.ubicacion}" />
                                <h:message for="txtUbicacion" /></td>
                        </tr>
                    </table>
                    <h:commandButton rendered="#{!areaBean.modificando}" 
                                     value="#{eti['boton.Agregar']}" 
                                     id="btnAgregar" action="#{areaBean.agregar()}"/>
                    
                    <h:commandButton rendered="#{areaBean.modificando}" 
                                     value="#{eti['boton.Modificar']}" 
                                     id="btnModificar" action="#{areaBean.modificarArea()}"/>
                    
                    <h:commandButton value="#{eti['boton.Buscar']}" 
                                     id="btnBuscar" 
                                     immediate="true"
                                     action="#{areaBean.buscar()}"/>
                    
                    <h:messages globalOnly="true"/>

                    <h:dataTable id="tablaAreas" value="#{areaBean.listaAreas}" border="1"
                                 binding="#{areaBean.tablaAreas}"
                                 var="areas">

                        <h:column id="col01">
                            <f:facet name="header">
                                <h:outputText value="Id "/>
                            </f:facet>
                            <h:outputText value="#{areas.idArea}"/>
                        </h:column>
                        <h:column id="col02">
                            <f:facet name="header">
                                <h:outputText value="Nombre"/>
                            </f:facet>
                            <h:outputText value="#{areas.nombre}"/>
                        </h:column>
                        <h:column id="col03">
                            <f:facet name="header">
                                <h:outputText value="Ubicacion"/>
                            </f:facet>
                            <h:outputText value="#{areas.ubicacion}"/>
                        </h:column>
                        <h:column id="colSeleccionar">
                            <f:facet name="header">
                            <h:outputText value="Seleccionar"/>
                            </f:facet>
                            <h:commandLink immediate="true" action="#{areaBean.seleccionar()}"  >
                                <h:graphicImage url="img/modificar.png"/>
                            </h:commandLink>
                        </h:column>
                        <h:column id="colEliminar">
                            <f:facet name="header">
                            <h:outputText value="Eliminar"/>
                            </f:facet>
                            <h:commandLink immediate="true" 
                                           onclick="return confirm ('esta seguro que quiere eliminar la Area')"
                                           action="#{areaBean.eliminar()}">
                                <h:graphicImage url="img/eliminar.jpg"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
            </h:form>
        </body>
    </html>
</f:view>

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
            <title>Mantenimiento de Matricula</title>
            <script src="assets/js/jquery-1.11.1.min.js" type="text/javascript"></script>
            <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
            <script src="//code.jquery.com/jquery-1.10.2.js"></script>
            <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

        </head>
        <body>
            <!--
            
           <f:loadBundle basename="cr.ac.una.icai.sicocaweb.etiquetas.recursos" var="eti"/>
        
            -->
            <h1><h:outputText value="Mantenimiento Empleados"/></h1>
            <h:form id="form1">
                <table>
                    <tr>
                        <td>Curso</td>
                        <td>
                                <h:selectOneMenu id="cbCurso"
                                                 value="#{matriculaNotaBean.elCuros.idCurso}"
                                                 required="true" requiredMessage="campo requerido">
                                    
                                <f:selectItems value="#{cursoBean.llenaCursos()}"/>
                                </h:selectOneMenu>
                                <h:message for="cbCurso"/>
                        </td>
                        
                        </tr>
                        <tr>
                            <td>Empleado</td>
                            <td><h:inputText 

                                    id="txtEmpleado" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{matriculaNotaBean.matricula.cedula}" />
                                <h:message for="txtEmpleado" /></td>
                   
                        </tr>
                        <tr>
                            <td>Nota</td>
                            <td><h:inputText
                                    id="txtNota" 
                                    value="#{matriculaNotaBean.matricula.nota}" />
                                <h:message for="txtNota" /></td>
                        </tr>

                    </table>
                    
                    <h:commandButton 
                                     value="#{eti['boton.Modificar']}" 
                                     id="btnModificar" action="#{matriculaNotaBean.modificarEmpleado()}"/>
                    
                    <h:commandButton value="#{eti['boton.Buscar']}" 
                                     id="btnBuscar" 
                                     action="#{matriculaNotaBean.buscar()}"/>
                    <h:commandButton value="#{eti['boton.Todos']}" 
                                     id="btnBuscarTodos" 
                                     action="#{matriculaNotaBean.verTodos()}"/>
                    
                    <h:messages globalOnly="true"/>

                    <h:dataTable id="tablaempleados" value="#{matriculaNotaBean.listaMatriculas}" border="1"
                                 binding="#{matriculaNotaBean.tablaMatricula}"
                                 var="matricula">

                        <h:column id="col01">
                            <f:facet name="header">
                                <h:outputText value="ID Curso"/>
                            </f:facet>
                            <h:outputText value="#{matricula.idCurso}"/>
                        </h:column>
                        <h:column id="col02">
                            <f:facet name="header">
                                <h:outputText value="ID Cedula"/>
                            </f:facet>
                            <h:outputText value="#{matricula.cedula}"/>
                        </h:column>
                        <h:column id="col03">
                            <f:facet name="header">
                                <h:outputText value="Nota"/>
                            </f:facet>
                            <h:outputText value="#{matricula.nota}"/>
                        </h:column>
                        <h:column id="colSeleccionar">
                            <f:facet name="header">
                            <h:outputText value="Seleccionar"/>
                            </f:facet>
                            <h:commandLink immediate="true" action="#{matriculaNotaBean.seleccionar()}"  >
                                <h:graphicImage url="img/modificar.png"/>
                            </h:commandLink>
                        </h:column>
                        <h:column id="colEliminar">
                            <f:facet name="header">
                            <h:outputText value="Eliminar"/>
                            </f:facet>
                            <h:commandLink immediate="true" 
                                           onclick="return confirm ('esta seguro que quiere eliminar la Area')"
                                           action="#{matriculaNotaBean.eliminar()}">
                                <h:graphicImage url="img/eliminar.jpg"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
            </h:form>
        </body>
    </html>
</f:view>

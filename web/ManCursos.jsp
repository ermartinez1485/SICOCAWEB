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
            <title>Mantenimiento de Cursos</title>
            <script src="assets/js/jquery-1.11.1.min.js" type="text/javascript"></script>
            <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
            <script src="//code.jquery.com/jquery-1.10.2.js"></script>
            <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
            <script>
                $(function() {
                $(".datepicker").datepicker({dateFormat: "yy-mm-dd"});
                });
            </script>

        </head>
        <body>
            <!--
            
            <f:loadBundle basename="cr.ac.una.icai.sicocaweb.etiquetas.recursos" var="eti"/>
        
            -->
            <h1><h:outputText value="Mantenimiento Cursos"/></h1>
            <h:form id="form1">
                <table>
                    <tr>
                        <td>ID Curso</td>
                        <td><h:inputText
                                disabled="#{cursoBean.modificando==true}"
                                id="txtIDCurso" 
                                required="true" requiredMessage="Requerido"
                                value="#{cursoBean.curso.idCurso}"/> 
                                <h:message for="txtIDCurso" />
                            </td>
                        </tr>
                        <tr>
                            <td>ID Instructor</td>
                            <td>
                                <h:selectOneMenu id="cbInstructores"
                                                 value="#{cursoBean.curso.idInstructor}"
                                                 required="true" requiredMessage="campo requerido">
                                    <f:selectItems value="#{instructorBean.llenaInstructores()}"/>
                                </h:selectOneMenu>
                                <h:message for="cbInstructores"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Nombre</td>
                            <td><h:inputText binding="#{cursoBean.inNombre}"
                                    id="txtNombre" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{cursoBean.curso.nombre}" />
                                <h:message for="txtNombre" /></td>
                        </tr>
                        <tr>
                            <td>Duracion en horas</td>
                            <td><h:inputText 
                                    id="txtDuracion" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{cursoBean.curso.horasDuracion}" />
                                <h:message for="txtDuracion" /></td>
                        </tr>
                        <tr>
                            <td>Fecha inicio</td>
                            <td><h:inputText
                                    styleClass="datepicker"
                                    id="txtFechaIni" 
                                    value="#{cursoBean.curso.fechaInicio}">
                                    <f:convertDateTime pattern="yyyy-MM-dd" />
                                </h:inputText>
                                <h:message for="txtFechaIni"/> </td>
                        </tr>
                        <tr>
                            <td>Fecha Finalizacion</td>
                            <td><h:inputText
                                    styleClass="datepicker"
                                    id="txtFechaFin" 
                                    value="#{cursoBean.curso.fechaFinalizacion}">
                                    <f:convertDateTime pattern="yyyy-MM-dd" />
                                </h:inputText>
                                <h:message for="txtFechaFin" /></td>
                        </tr>
                        <tr>
                            <td>Precio</td>
                            <td><h:inputText 
                                    id="txtPrecio" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{cursoBean.curso.precio}" />
                                <h:message for="txtPrecio" /></td>
                        </tr>
                        
                    </table>
                    <h:commandButton rendered="#{!cursoBean.modificando}" 
                                     value="#{eti['boton.Agregar']}" 
                                     id="btnAgregar" action="#{cursoBean.agregar()}"/>
                    
                    <h:commandButton rendered="#{cursoBean.modificando}" 
                                     value="#{eti['boton.Modificar']}" 
                                     id="btnModificar" action="#{cursoBean.modificarCurso()}"/>
                    
                    <h:commandButton value="#{eti['boton.Buscar']}" 
                                     id="btnBuscar" 
                                     immediate="true"
                                     action="#{cursoBean.buscar()}"/>
                    
                    <h:messages globalOnly="true"/>

                    <h:dataTable id="tablacursos" value="#{cursoBean.listaCursos}" border="1"
                                 binding="#{cursoBean.tablaCursos}"
                                 var="cursos">

                        <h:column id="col01">
                            <f:facet name="header">
                                <h:outputText value="Id "/>
                            </f:facet>
                            <h:outputText value="#{cursos.idCurso}"/>
                        </h:column>
                        <h:column id="col02">
                            <f:facet name="header">
                                <h:outputText value="Nombre"/>
                            </f:facet>
                            <h:outputText value="#{cursos.nombre}"/>
                        </h:column>
                        <h:column id="col03">
                            <f:facet name="header">
                                <h:outputText value="Duracion en horas"/>
                            </f:facet>
                            <h:outputText value="#{cursos.horasDuracion}"/>
                        </h:column>
                        <h:column id="col04">
                            <f:facet name="header">
                                <h:outputText value="Fecha inicio"/>
                            </f:facet>
                            <h:outputText value="#{cursos.fechaInicio}"/>
                        </h:column>
                        <h:column id="col05">
                            <f:facet name="header">
                                <h:outputText value="Fecha Finalizacion"/>
                            </f:facet>
                            <h:outputText value="#{cursos.fechaFinalizacion}"/>
                        </h:column>
                        <h:column id="col06">
                            <f:facet name="header">
                                <h:outputText value="Precio"/>
                            </f:facet>
                            <h:outputText value="#{cursos.precio}"/>
                        </h:column>
                        <h:column id="col07">
                            <f:facet name="header">
                                <h:outputText value="ID Instructor"/>
                            </f:facet>
                            <h:outputText value="#{cursos.idInstructor}"/>
                        </h:column>
                        <h:column id="colSeleccionar">
                            <f:facet name="header">
                            <h:outputText value="Seleccionar"/>
                            </f:facet>
                            <h:commandLink immediate="true" action="#{cursoBean.seleccionar()}"  >
                                <h:graphicImage url="img/modificar.png"/>
                            </h:commandLink>
                        </h:column>
                        <h:column id="colEliminar">
                            <f:facet name="header">
                            <h:outputText value="Eliminar"/>
                            </f:facet>
                            <h:commandLink immediate="true" 
                                           onclick="return confirm ('esta seguro que quiere eliminar la Area')"
                                           action="#{cursoBean.eliminar()}">
                                <h:graphicImage url="img/eliminar.jpg"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
            </h:form>
        </body>
    </html>
</f:view>

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
            <title>Mantenimiento de Instructores</title>
        </head>
        <body>
            <!--
            
            <f:loadBundle basename="cr.ac.una.icai.sicocaweb.etiquetas.recursos" var="eti"/>
        
            -->
            <h1><h:outputText value="Mantenimiento Instructores"/></h1>
            <h:form id="form1">
                <table>
                    <tr>
                        <td>ID Instructor</td>
                        <td><h:inputText
                                disabled="#{instructorBean.modificando==true}"
                                id="txtIDInstructor" 
                                required="true" requiredMessage="Requerido"
                                value="#{instructorBean.instructor.idInstructor}"/> 
                                <h:message for="txtIDInstructor" />
                            </td>
                        </tr>
                        <tr>
                            <td>Nombre</td>
                            <td><h:inputText binding="#{instructorBean.inNombre}"
                                    id="txtNombre" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{instructorBean.instructor.nombre}" />
                                <h:message for="txtNombre" /></td>
                        </tr>
                        <tr>
                            <td>Grado Academico</td>
                            <td><h:inputText 
                                    id="txtGrado" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{instructorBean.instructor.gradoAcademico}" />
                                <h:message for="txtGrado" /></td>
                        </tr>
                        <tr>
                            <td>Años Experiencia</td>
                            <td><h:inputText 
                                    id="txtannosExp" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{instructorBean.instructor.annosExperiencia}" />
                                <h:message for="txtannosExp" /></td>
                        </tr>
                        <tr>
                            <td>Telefono</td>
                            <td><h:inputText 
                                    id="txtTelefono" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{instructorBean.instructor.telefono}" />
                                <h:message for="txtTelefono" /></td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td><h:inputText 
                                    id="txtCorreo" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{instructorBean.instructor.correo}" />
                                <h:message for="txtCorreo" /></td>
                        </tr>
                    </table>
                    <h:commandButton rendered="#{!instructorBean.modificando}" 
                                     value="#{eti['boton.Agregar']}" 
                                     id="btnAgregar" action="#{instructorBean.agregar()}"/>
                    
                    <h:commandButton rendered="#{instructorBean.modificando}" 
                                     value="#{eti['boton.Modificar']}" 
                                     id="btnModificar" action="#{instructorBean.modificarInstructor()}"/>
                    
                    <h:commandButton value="#{eti['boton.Buscar']}" 
                                     id="btnBuscar" 
                                     immediate="true"
                                     action="#{instructorBean.buscar()}"/>
                    
                    <h:messages globalOnly="true"/>

                    <h:dataTable id="tabinstructors" value="#{instructorBean.listaInstructores}" border="1"
                                 binding="#{instructorBean.tablaInstructores}"
                                 var="instructores">

                        <h:column id="col01">
                            <f:facet name="header">
                                <h:outputText value="Id "/>
                            </f:facet>
                            <h:outputText value="#{instructores.idInstructor}"/>
                        </h:column>
                        <h:column id="col02">
                            <f:facet name="header">
                                <h:outputText value="Nombre"/>
                            </f:facet>
                            <h:outputText value="#{instructores.nombre}"/>
                        </h:column>
                        <h:column id="col03">
                            <f:facet name="header">
                                <h:outputText value="Grado Academico"/>
                            </f:facet>
                            <h:outputText value="#{instructores.gradoAcademico}"/>
                        </h:column>
                        <h:column id="col04">
                            <f:facet name="header">
                                <h:outputText value="Años de Experiencia"/>
                            </f:facet>
                            <h:outputText value="#{instructores.annosExperiencia}"/>
                        </h:column>
                        <h:column id="col05">
                            <f:facet name="header">
                                <h:outputText value="Telefono"/>
                            </f:facet>
                            <h:outputText value="#{instructores.telefono}"/>
                        </h:column>
                        <h:column id="col06">
                            <f:facet name="header">
                                <h:outputText value="Email"/>
                            </f:facet>
                            <h:outputText value="#{instructores.correo}"/>
                        </h:column>
                        <h:column id="colSeleccionar">
                            <f:facet name="header">
                            <h:outputText value="Seleccionar"/>
                            </f:facet>
                            <h:commandLink immediate="true" action="#{instructorBean.seleccionar()}"  >
                                <h:graphicImage url="img/modificar.png"/>
                            </h:commandLink>
                        </h:column>
                        <h:column id="colEliminar">
                            <f:facet name="header">
                            <h:outputText value="Eliminar"/>
                            </f:facet>
                            <h:commandLink immediate="true" 
                                           onclick="return confirm ('esta seguro que quiere eliminar la Area')"
                                           action="#{instructorBean.eliminar()}">
                                <h:graphicImage url="img/eliminar.jpg"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
            </h:form>
        </body>
    </html>
</f:view>

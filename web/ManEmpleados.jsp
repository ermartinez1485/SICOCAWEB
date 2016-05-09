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
            <title>Mantenimiento de Empleados</title>
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
                        <td>Cedula</td>
                        <td><h:inputText
                                disabled="#{empleadoBean.modificando==true}"
                                id="txtCedula" 
                                required="true" requiredMessage="Requerido"
                                value="#{empleadoBean.empleado.cedula}"/> 
                                <h:message for="txtCedula" />
                            </td>
                        </tr>
                        <tr>
                            <td>Nombre</td>
                                <td><h:inputText binding="#{empleadoBean.inNombre}"
                                    id="txtNombre" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{empleadoBean.empleado.nombre}" />
                                <h:message for="txtNombre" /></td>
                        </tr>
                        <tr>
                            <td>Apellido P</td>
                            <td><h:inputText
                                    id="txtApellido1" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{empleadoBean.empleado.apellido1}" />
                                <h:message for="txtApellido1" /></td>
                        </tr>
                        <tr>
                            <td>Apellido M</td>
                            <td><h:inputText
                                    id="txtApellido2" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{empleadoBean.empleado.apellido2}" />
                                <h:message for="txtApellido2" /></td>
                        </tr>
                        <tr>
                            <td>Telefono</td>
                            <td><h:inputText 
                                    id="txtTelefono" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{empleadoBean.empleado.telefono}" />
                                <h:message for="txtTelefono" /></td>
                        </tr>
                        <tr>
                            <td>Correo</td>
                            <td><h:inputText
                                    id="txtCorreo" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{empleadoBean.empleado.correo}" >
                                </h:inputText>
                                <h:message for="txtCorreo"/> </td>
                        </tr>
                        <tr>
                            <td>ID Area</td>
                            <td>
                                <h:selectOneMenu id="cbArea"
                                                 value="#{empleadoBean.laArea.idArea}"
                                                 required="true" requiredMessage="campo requerido">
                                    <f:selectItems value="#{areaBean.llenaAreas()}"/>
                                </h:selectOneMenu>
                                <h:message for="cbArea"/>
                            </td>
                        </tr>

                    </table>
                    <h:commandButton rendered="#{!empleadoBean.modificando}" 
                                     value="#{eti['boton.Agregar']}" 
                                     id="btnAgregar" action="#{empleadoBean.agregar()}"/>
                    
                    <h:commandButton rendered="#{empleadoBean.modificando}" 
                                     value="#{eti['boton.Modificar']}" 
                                     id="btnModificar" action="#{empleadoBean.modificarEmpleado()}"/>
                    
                    <h:commandButton value="#{eti['boton.Buscar']}" 
                                     id="btnBuscar" 
                                     immediate="true"
                                     action="#{empleadoBean.buscar()}"/>
                    
                    <h:messages globalOnly="true"/>

                    <h:dataTable id="tablaempleados" value="#{empleadoBean.listaEmpleados}" border="1"
                                 binding="#{empleadoBean.tablaEmpleados}"
                                 var="empleados">

                        <h:column id="col01">
                            <f:facet name="header">
                                <h:outputText value="Cedula"/>
                            </f:facet>
                            <h:outputText value="#{empleados.cedula}"/>
                        </h:column>
                        <h:column id="col02">
                            <f:facet name="header">
                                <h:outputText value="Nombre"/>
                            </f:facet>
                            <h:outputText value="#{empleados.nombre}"/>
                        </h:column>
                        <h:column id="col03">
                            <f:facet name="header">
                                <h:outputText value="Apellido P"/>
                            </f:facet>
                            <h:outputText value="#{empleados.apellido1}"/>
                        </h:column>
                        <h:column id="col04">
                            <f:facet name="header">
                                <h:outputText value="Apellido M"/>
                            </f:facet>
                            <h:outputText value="#{empleados.apellido2}"/>
                        </h:column>
                        <h:column id="col05">
                            <f:facet name="header">
                                <h:outputText value="Telefono"/>
                            </f:facet>
                            <h:outputText value="#{empleados.telefono}"/>
                        </h:column>
                        <h:column id="col06">
                            <f:facet name="header">
                                <h:outputText value="Correo"/>
                            </f:facet>
                            <h:outputText value="#{empleados.correo}"/>
                        </h:column>
                        <h:column id="col07">
                            <f:facet name="header">
                                <h:outputText value="ID Area"/>
                            </f:facet>
                            <h:outputText value="#{empleados.idArea}"/>
                        </h:column>
                        <h:column id="colSeleccionar">
                            <f:facet name="header">
                            <h:outputText value="Seleccionar"/>
                            </f:facet>
                            <h:commandLink immediate="true" action="#{empleadoBean.seleccionar()}"  >
                                <h:graphicImage url="img/modificar.png"/>
                            </h:commandLink>
                        </h:column>
                        <h:column id="colEliminar">
                            <f:facet name="header">
                            <h:outputText value="Eliminar"/>
                            </f:facet>
                            <h:commandLink immediate="true" 
                                           onclick="return confirm ('esta seguro que quiere eliminar la Area')"
                                           action="#{empleadoBean.eliminar()}">
                                <h:graphicImage url="img/eliminar.jpg"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
            </h:form>
        </body>
    </html>
</f:view>

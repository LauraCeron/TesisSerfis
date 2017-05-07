<%-- 
    Document   : materia
    Created on : 30/04/2017, 23:14:57
    Author     : Lau Cerón
--%>

<%@page import="BEAN.BeanArea"%>
<%@page import="java.util.List"%>
<%@page import="MODEL.clsArea"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Materia</h1>
        <form action="datMateria" method="post">
            <table>
                <tr>
                <td>
            <labe>Nombre Materia</labe>
                </td>
                <td>
                    <input name="nombreMateria" style="width:150px;height:20px">
                </td>
            </tr>
            
            <tr>
                <td>
                    <label>Area</label>
                </td>
                <td>
                    <select name="cmbArea" style="width:150px;height:25px">
                        <option value="0" selected>(Seleccione)</option>
                        <%
                            BeanArea beanArea = new BeanArea();
                            List<clsArea> listaArea= beanArea.listarArea();
                            for(clsArea area: listaArea)
                            { %>
                            <option value="<%=area.getCodArea()%>"><%=area.getNombreArea()%></option> 
                         <%   } %>
                       
                    </select>
                </td>
            </tr>
            <tr>
                <td>
            <labe>Estado</labe>
                </td>
                <td>
                    <input name="estado"  type="checkbox">
                </td>
            </tr>
                 <tr>
                <td>
                    <input type='submit' name='btnGuardar' value="Guardar"/>
                </td>
            </tr>
            </table>
        </form>
    </body>
</html>

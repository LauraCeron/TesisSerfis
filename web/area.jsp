<%-- 
    Document   : area
    Created on : 30/04/2017, 23:09:21
    Author     : Lau Cerón
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Area</h1>
        <form action="datArea" method="post">
            <table>
                <tr>
                <td>
            <labe>Nombre Area</labe>
                </td>
                <td>
                    <input name="nombreArea" style="width:150px;height:20px">
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
        
        <form action="datArea" method="GET">
            <table>
            </tr>
                 <tr>
                <td>
                    <input type='submit' name='btnConsultar' value="Consultar"/>
                </td>
            </tr>
            </table>
        </form>
            
    </body>
</html>

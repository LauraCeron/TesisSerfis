<%-- 
    Document   : listarArea
    Created on : 06/05/2017, 21:13:34
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
        <h1>Hello World!</h1>
        <form>
            <grd:dbgrid id="tblStat" name="tblStat" width="100" pageSize="10" 

     border="0" cellSpacing="1" cellPadding="2" 

     cssClass="gridTable">
 <grd:gridpager imgFirst="images/First.gif" imgPrevious="images/Previous.gif" 

      imgNext="images/Next.gif" imgLast="images/Last.gif"/>
 <grd:gridsorter/>
 <grd:rownumcolumn headerText="#" width="5" HAlign="right"/>
 <grd:imagecolumn headerText="" width="5" HAlign="center" 

      imageSrc="images/Edit.gif" 
      imageBorder="0" imageWidth="16" imageHeight="16" 

      alterText="Click to edit"/>
 <grd:textcolumn dataField="CLICLIENT" headerText="Client" 

      width="10" sortable="true"/>
 <grd:textcolumn dataField="CLIDESCRIPTION" headerText="Description" 

      width="50" sortable="true"/>
 <grd:decodecolumn dataField="CLIENABLED" headerText="Enabled" width="10" 

      decodeValues="Y,N" displayValues="Yes,No" valueSeperator=","/>
 <grd:datecolumn dataField="CLIUPDSTAMP" headerText="Last Updated" 

      dataFormat="dd/MM/yyyy HH:mm:ss" width="20"/>
</grd:dbgrid>
        </form>
    </body>
</html>

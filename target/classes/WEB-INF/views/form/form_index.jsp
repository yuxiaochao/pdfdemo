<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>承德鑫澳食品有限公司成品表单</title>
<script src="/js/sweet-alert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/sweet-alert.css">
</head>
<body>
	<table border="1" bordercolor="#a0c6e5"
		style="border-collapse: collapse;" id="headtable">
		<tr>
			<td>客户名称</td>
			<td width="88%"><input type="text" style="border: none;width: 486px;"
				id="fill_113"></td>
		</tr>
		<tr>
			<td>地址</td>
			<td><input type="text" style="border: none;width: 486px;" id="fill_114"></td>

		</tr>
		<tr>
			<td>联系方式</td>
			<td><input type="text" style="border: none;width: 486px;" id="fill_115"></td>
		</tr>
	</table>


	<table border="1" bordercolor="#a0c6e5"
		style="border-collapse: collapse; width : 200px; height : 50px ; cellspacing : 0 ; cellpadding : 0;" id="formtable">

		<tr>
			<th>序号</th>
			<th>品名</th>
			<th>单位</th>
			<th>数量</th>
			<th>单价</th>
			<th>备注</th>
			<th>合计</th>
			<th>手提袋（个）</th>
		</tr>
		<c:forEach begin="0" end="14" step="1" var="ll">
			<tr class="eee">
				<td>${ll+1}</td>
				<td><input type="text" style="border: none;"
					pk="fill_${7*ll+1}"></td>
				<td><input type="text" style="border: none;"
					pk="fill_${7*ll+2}"></td>
				<td><input type="text" style="border: none;"
					pk="fill_${7*ll+3}"></td>
				<td><input type="text" style="border: none;"
					pk="fill_${7*ll+4}"></td>
				<td><input type="text" style="border: none;"
					pk="fill_${7*ll+5}"></td>
				<td><input type="text" style="border: none;"
					pk="fill_${7*ll+6}"></td>
				<td><input type="text" style="border: none;"
					pk="fill_${7*ll+7}"></td>
			</tr>
		</c:forEach>
	</table>
	<div style=" margin-left: 183px;">
		<span style="font-size: 30px;">随存货物</span>
	</div>
	<table border="1" bordercolor="#a0c6e5"
		style="border-collapse: collapse;" id="sctable">
		<tr>
			<c:forEach begin="1" end="3" step="1" var="ll">
				<td><span>${ll}</span><input type="text" id="${ll}"
					style="border: none;"></td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach begin="4" end="6" step="1" var="ll">
				<td><span>${ll}</span><input type="text" id="${ll}"
					style="border: none;"></td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach begin="7" end="9" step="1" var="ll">
				<td><span>${ll}</span><input type="text" id="${ll}"
					style="border: none;"></td>
			</c:forEach>
		</tr>
	</table>
	<div style=" margin-left: 183px;">
		<span style="font-size: 30px;">备注</span>
	</div>
	<table border="1" bordercolor="#a0c6e5"
		style="border-collapse: collapse;" id="bztable">

		<tr>
			<td style="width: 115%"><input type="text" id="10" style="border: none;width: 530px">
		</tr>
		<tr>
			<td><input type="text" id="11" style="border: none;width: 530px">
		</tr>
	</table>
	<button id="goto" style="top: 526px;left: 567px;width: 150px;height: 72px;background-color: green;font-size: 25px;position: absolute;">保存</button>
	<script src="/js/jquery.js"></script>
	<script type="text/javascript">
		$("#goto").click(
				function() {
					var parameterMap = {}
					var formtable = $("#formtable tr");
					for (var i = 1; i < formtable.length + 1; i++) {
						debugger
						for (var j = 1; j < 8; j++) {
							var pk = $("table tr:eq(" + i + ")").children(
									"td:eq(" + j + ")").find("input")
									.attr("pk");
							var val = $("table tr:eq(" + i + ")").children(
									"td:eq(" + j + ")").find("input").val();
							parameterMap[pk] = val;//这里的key才能是动态生成的 
						}
					}
					for (var i = 1; i < 12; i++) {
						var val = $("#" + i + "").val();
						parameterMap[i] = val;//这里的key才能是动态生成的 
					}
					var fill_113 = $("#fill_113").val();
					var fill_114 = $("#fill_114").val();
					var fill_115 = $("#fill_115").val();
					parameterMap["fill_113"] = fill_113;
					parameterMap["fill_114"] = fill_114;
					parameterMap["fill_115"] = fill_115;
					var parameter = JSON.stringify(parameterMap);
					$.ajax({
						url : "/form/formtable.html",
						data : {
							"parameterMap" : parameterMap
						},
						type : "post",
						success : function(data) {
							if(data.status=="SUCCESS"){
								swal("系统提示", "生成PDF成功", "success");
							}else if(data.status=="FAIL"){
								sweetAlert("系统提示", "生成PDF失败", "error");
							}
						}
					});
				});
	</script>
</body>
</html>
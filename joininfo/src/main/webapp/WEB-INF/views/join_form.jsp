<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function set_gender(){
  switch(document.input_form.jumin2.value.substr(0,1))
  {
  case "1":
  case "3":
    document.input_form.gender[0].checked=true;
    break;
  case "2":
  case "4":
    document.input_form.gender[1].checked=true;
    break;
  }
}
function num_check(str){
  var i;
  for(i=0; i<str.length; i++){
    if((str.substring(i, i+1)<"0" || str.substring(i, i+1)>"9"))
      {
        return false;
      }
  }
  return true;
}
function check_input(){
  var str, i, ch, chk, chk_list="";
  //���̵� üũ ---->
  if(document.input_form.id.value ==""){
    alert("���̵� �Է��ϼ���!!!");
    document.input_form.id.focus();
    return false;
  }
  else{
    str = document.input_form.id.value;
    if(str.length <6|| str.length>10){
      alert("���̵� ���� Ȯ��(6~10�ڸ�)!!!");
      document.input_form.id.focus();
      return false;
    }
    else{
      for(i=0; i<str.length; i++){
        ch=str.substring(i, i+1);
        if(!((ch>= "0"&& ch<="9") || (ch>="a" && ch<="z")|| (ch>="A" && ch<="Z")))
          {
          alert("Ư������ ����, �ٽ� �Է� !!!");
          document.input_form.id.focus();
          return false;
          }
      }
    }
  } // ���̵� üũ <---
  chk_list = "id =" +str +"\n";
  
  // �н����� üũ ---->
  if(document.input_form.pw.value ==""){
    alert("�н����带 �Է��ϼ���!!!");
    document.input_form.pw.focus();
    return false;
  }
  else {
    str= document.input_form.pw.value;
    if(str.length < 6|| str.length >10){
      alert("�н����� ���� Ȯ��(6~10�ڸ�)!!!");
      document.input_form.pw.focus();
      return false;
    }
    else{
      for(i=0; i<str.length; i++){
        ch=str.substring(i, i+1);
        if(!((ch>= "0"&& ch<="9") || (ch>="a" && ch<="z")|| (ch>="A" && ch<="Z")))
          {
          alert("Ư������ ����, �ٽ� �Է� !!!");
          document.input_form.pw.focus();
          return false;
          }
    }
  }
}
  if(document.input_form.pw.value != document.input_form.pw2.value){
    alert("�н����� �Է� ����!!!")
    document.input_form.pw2.focus();
    return false;
  }//�н����� üũ <----
  chk_list += "pw=" + str +"\n"
  // �ֹι�ȣ üũ ---->
  if(document.input_form.jumin1.value==""){
    alert("�ֹι�ȣ (���ڸ�)�� �Է��ϼ���!!!");
    document.input_form.jumin1.focus();
    return false;
  }
  else if(document.input_form.jumin2.value ==""){
    alert("�ֹι�ȣ(���ڸ�)�� �Է��ϼ���!!!");
    document.input_form.jumin2.focus();
    return false;
    }
  else{
    var str1 = document.input_form.jumin1.value;
    var str2 = document.input_form.jumin2.value;
    if(str1.length != 6){
      alert("�ֹι�ȣ Ȯ��(������� 6�ڸ�)!!!");
      document.input_form.jumin1.focus();
      return false;
    }
    else if(str2.length != 7){
      alert("�ֹι�ȣ Ȯ��(7�ڸ�)!!!");
      document.input_form.jumin2.focus();
      return false;
    }
  }// �ֹι�ȣ üũ <----
  chk_list += "�ֹι�ȣ =" + document.input_form.jumin1.value + "-"
        + document.input_form.jumin2.value +"\n";
  
  // ���� üũ  --->
  if(!(document.input_form.gender[0].checked == true || document.input_form.gender[1].checked == true))
    {
      alert("������ �����ϼ��� !!!");
      return false;
    }// ���� üũ <---
    if (document.input_form.gender[0].checked == true)
      str = document.input_form.gender[0].value;
    else
      str= document.input_form.gender[1].value;
    
    chk_list += "���� =" + str + "\n";
    
    // ��ȭ��ȣ üũ --->
    if(document.input_form.tel1.value.length==3){
      if(!num_check(document.input_form.tel1.value)){
        alert("��ȭ��ȣ ��3�ڸ� �Է� ���� !!!");
        document.input_form.tel1.focus();
        return false;
      }
    }
    else{
      alert("��ȭ��ȣ ��3�ڸ� �Է��ϼ���!!!");
      document.input_form.tel1.focus();
      return false;
    }
    if(document.input_form.tel2.value.length==4){
      if(!num_check(document.input_form.tel2.value)){
        alert("��ȭ��ȣ �߰�4�ڸ� �Է� ���� !!!");
        document.input_form.tel2.focus();
        return false;
      }
    }
    else{
      alert("��ȭ��ȣ �߰�4�ڸ� �Է��ϼ���!!!");
      document.input_form.tel2.focus();
      return false;
    }
    if(document.input_form.tel3.value.length==4){
      if(!num_check(document.input_form.tel3.value)){
        alert("��ȭ��ȣ ��4�ڸ� �Է� ���� !!!");
        document.input_form.tel3.focus();
        return false;
      }
    }
    else{
      alert("��ȭ��ȣ ��4�ڸ� �Է��ϼ���!!!");
      document.input_form.tel3.focus();
      return false;
    }
    chk_list += "��ȭ��ȣ =" +document.input_form.tel1.value +"-"
          + document.input_form.tel2.value +"-"
          + document.input_form.tel3.value +"\n";
    //�̸���(�պκ�) üũ ---->
    if(document.input_form.email1.value==""){
      alert("�̸���(�պκ�)�� �Է��ϼ���");
      document.input_form.email1.focus();
      return false;
    }//�̸���(��) üũ <---
    //�̸���(��) üũ ---->
    if(document.input_form.email2.value==""){
      alert("�̸���(�޺κ�)�� �Է��ϼ���");
      document.input_form.email2.focus();
      return false;
    }//�̸���(��) üũ <---
    chk_list += "�̸��� =" + document.input_form.email1.value +"@"
          + document.input_form.email2.value;
    //��� üũ ---->
    if(!(document.input_form.hobby[0].checked ==true ||
        document.input_form.hobby[1].checked ==true ||
        document.input_form.hobby[2].checked ==true ||
        document.input_form.hobby[3].checked ==true ||
        document.input_form.hobby[4].checked ==true ))
      {
      alert("��̸� �����ϼ���!!!");
      return false;
      }//���üũ<---
      str="";
      for(i=0; i<document.input_form.hobby.length; i++){
        if(document.input_form.hobby[i].checked == true)
          str += document.input_form.hobby[i].value + " ";
      }
      chk_list += "��� ="+ str + "\n";
    //  �ڱ�Ұ� üũ <----
    chk_list += "�ڱ�Ұ� ="+ document.input_form.intro.value +"\n";
    
    alert(chk_list);
    return true;
    //document.input_form.submit(); // ������ ����
}
</script>
</head>
<body>
<form name="input_form" action="input.bo" method="post" onsubmit="return check_input();">
<!-- <form name ="input_form" action ="input_form.jsp" method="post" onsubmit="return check_input();">  -->
<!--  <form name="input_form" action="input_form.jsp" method="port"> -->
<table align ="center" border ="1">
<tr>
  <td width="110"> ���̵� </td>
  <td width="400">
    <input type ="text" name ="id" size ="30" style="ime-mode:inactive"
    placeholder="�ּ�6~�ִ�10, ���ڿ;��ĺ��� ���">
    </td>
</tr>
<tr>
  <td width="110"> ��й�ȣ </td>
  <td width="400">
    <input type ="password" name ="pw" size ="30" style="ime-mode:inactive"
    placeholder="�ּ�6~�ִ�10, ���ڿ;��ĺ��� ���">
    </td>
</tr>
<tr>
  <td width="110"> ��й�ȣ Ȯ�� </td>
  <td width="400">
    <input type ="password" name ="pw2" size ="30" style="ime-mode:inactive"
    placeholder="�ּ�6~�ִ�10, ���ڿ;��ĺ��� ���">
    </td>
</tr>
<tr>
  <td width="110"> �ֹι�ȣ </td>
  <td width="400">
    <input type ="text" name ="jumin1" size ="6"  maxlength="6"> - 
    <input type ="text" name ="jumin2" size ="7"  maxlength="7" onblur="set_gender();"> 
    </td>
</tr>
<tr>
  <td width="110"> ���� </td>
  <td width="400">
    <input type ="radio" name ="gender" value= "����"> ���� 
    <input type ="radio" name ="gender" value= "����"> ����
    </td>
</tr>
<tr>
  <td > ��ȭ��ȣ </td>
  <td >
    <input type ="tel" name ="tel1" size="3" maxlength="3"> - 
    <input type ="tel" name ="tel2" size="4" maxlength="4"> - 
    <input type ="tel" name ="tel3" size="4" maxlength="4">  
  </td>
</tr>
<tr>
  <td width="110"> �̸��� </td>
  <td width="400">
    <input type ="text" name ="email1" size="10" style="ime-mode:inactive"> @ 
    <select name="email2">
      <option value="">�����ּҼ���
      <option value="hanmail.net">hanmail.net
      <option value="naver.com">naver.com
      <option value="nate.com">nate.com
    </select>
  </td>
</tr>
<tr>
  <td width="110"> ��� </td>
  <td width="400">
    <input type="checkbox" name="hobby" value="���"> ���
    <input type="checkbox" name="hobby" value="����"> ����
    <input type="checkbox" name="hobby" value="��Ű"> ��Ű
    <input type="checkbox" name="hobby" value="����"> ����
    <input type="checkbox" name="hobby" value="��ȭ"> ��ȭ
  </td>
</tr>
<tr>
  <td width="110"> �ڱ�Ұ� </td>
  <td width="400"><textarea name ="intro" rows="5" cols="50" style="ime-mode:active"></textarea></td>
</tr>
<tr>
<td colspan="2" align="center" width="500">
   <input type="submit" value="�Է�">
 <!--  <input type="button" value="�Է�" onclick="check_input();"> -->  
    <input type="reset" name="reset" value="���">
  </td>
</tr>
</table>
</form>
</body>
</html>
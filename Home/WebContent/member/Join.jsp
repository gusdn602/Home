<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test</title>
<link rel="stylesheet" href="../css/w3.css" />
<link rel="stylesheet" href="../css/join.css" />
<script type="text/javascript" src="../js/jquery-3.5.0.min.js"></script>
<style>

</style>
<script type="text/javascript">
	$(document).ready(function(){
		/*
			비동기 통신
				==> 웹서비스에서 원칙은 
					클라이트가 요청을 하면 
					서버는 클라이언트가 요청한 문서를 응답(전송)해준다.
					그리고 그 직후
					서버는 클라이언트와의 연결을 끊는다.
					따라서 이런 통신방식을
					단절형 통신이라 이야기한다.
					
					그리고
					서버가 전달된 문서는 
					원칙은 클라이언트가 전송받은 문서와 동일해야 된다.
					이런 경우를 동기형이라 이야기 한다.
					
					반대로
					서버가 전달해준 문서와
					클라이언트가 받은 문서를 일부분만 교체한 경우
					비동기라 이야기한다.
					
					결론적으로
					비동기처리란
					서버가 보내준 문서의 일부분만 서버와 통신을 해서 새로 받아서 교체해서
					이부분만 교체하는 처리를 
					비동기 처리라 한다.
					영어로 Ajax 라 부른다.
					
					그런데
					이 비동기 처리는 jQuery에서 함수로 정의가 되어있고
						형식 ]
							
							$.ajax({
								url: '주소',			==> 요청주소
								type: 'get | post',		==> 데이터 전송방식
								dataType: 'text | html | xml | json',	==> 데이터 전송 타입
								data: {						==> 전송될 데이터(파라미터들)
									넘겨줄 데이터 나열...
									'변수이름': '데이터',
									'변수이름': '데이터',
									.....
								},
								success: function(data){	==> 통신에 성공할 경우 실행할 함수
									성공했을 때 처리내용..
								},
								error: function(){			==> 통신에 실패할 경우 실행할 함수
									실패시 처리내용
								}
							});
		*/
		
		$('#idck').click(function(){
			// 할일
			// 1. 입력태그에 입력된 아이디를 가져오고
			var sid = $('#id').val();
			if(sid){
				$.ajax({
					url: '../id.check',
					type: 'post',
					dataType: 'json',
					data: {
						'id': sid
					},
					success: function(data){
						var result = data.result;
						if(result == 'ok'){
							// 아이디를 사용가능한 경우
							$('#idmsg').text('*** 사용 가능한 아이디 입니다. ***');
							$('#idmsg').css('color', 'blue');
							$('#idmsg').css('display', '');
							
						} else {
							// 아이디가 사용불가능한 경우
							$('#idmsg').text('### 사용할 수 없는 아이디 입니다. ###');
							$('#idmsg').css('color', 'red');
							$('#idmsg').css('display', '');
							$('#id').val('');
							$('#id').focus();
						}
					},
					error: function(){
						alert('### 통신 실패 ###');
					}
				});
			} else {
				$('#id').focus();
				return;
			}
		});
	});
</script>
</head>
<body>
	<div class="w3-col l3 m3">
		<p></p>
	</div>
	<div class="w3-col l6 m6 s12">
		<form class="w3-col" method="post" action="" name="frm" id="frm">
			<div class="w3-col w3-center w3-blue w3-margin-top">
				<h2>Increpas Join</h2>
			</div>
			<div class="w3-col w3-padding-32 w3-margin-top">
				<div class="w3-col">
					<label class="w3-col l3 m3 w3-right-align w3-padding" for="name">회 원 이 름 : </label>
					<div class="w3-col l9 m12 w3-padding">
						<input class="w3-col" type="text" id="name" name="name">
					</div>
				</div>
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="id">회원 아이디 : </label>
					<div class="w3-col m9 w3-padding">
						<div class="w3-col m9 pright">
							<input class="w3-col" type="text" id="id" name="id">
						</div>
						<div class="w3-col m3 w3-red w3-hover-orange w3-button w3-small w3-card btn1" id="idck">ID Check</div>
						<p class="w3-col w3-center" id="idmsg" style="display: none;"></p>
						<div class="w3-col w3-center" id="idCont" style="display: none;">
							<h4 class="w3-col m6">I D : </h4><h4 class="w3-col m6" id="getId"></h4>
							<h4 class="w3-col m6">NAME : </h4><h4 class="w3-col m6" id="getName"></h4>
							<h4 class="w3-col m6">TEL : </h4><h4 class="w3-col m6" id="getTel"></h4>
							<h4 class="w3-col m6">MAIL : </h4><h4 class="w3-col m6" id="getMail"></h4>
							<!-- 
									문제 ]
										아이티체크 버튼을 클릭했을 경우
										입력한 아이디가 존해하면 해당 아이디의 정보를 조회해서
										idCont 태그에 추가하고 보여주세요.
								
							 -->
						</div>
					</div>
				</div>
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="pw">비 밀 번 호 : </label>
					<div class="w3-col m9 w3-padding">
						<input class="w3-col" type="password" id="pw" name="pw">
					</div>
				</div>
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="repw">비밀번호확인 : </label>
					<div class="w3-col m9 w3-padding">
						<input class="w3-col" type="password" id="repw" name="reipw">
					</div>
				</div>
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="file">프로필 사진 : </label>
					<div class="w3-col m9 w3-padding w3-center" id="imgfr">
						<input class="w3-col" type="file" id="file" name="file">
						<img class="imgwin w3-border w3-margin-top w3-margin-bottom dnone" id="img1" src="../img/noimage.jpg" alt="Image">
					</div>
				</div>
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="pw">회원 이메일 : </label>
					<div class="w3-col m9 w3-padding">
						<input class="w3-col" type="text" id="mail" name="mail">
					</div>
				</div>
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="tel">전 화 번 호 : </label>
					<div class="w3-col m9 w3-padding">
						<input class="w3-col" type="text" id="tel" name="tel">
					</div>
				</div>
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="gen">성 별 : </label>
					<div class="w3-col m9 w3-padding w3-center">
						<div class="w3-half">
							<input type="radio" id="mgen" name="gen">
							<label for="mgen"> 남 자</label>
						</div>
						<div class="w3-half">
							<input type="radio" id="wgen" name="gen">
							<label for="wgen"> 여 자</label>
						</div>
					</div>
				</div>
				<!-- ----------------------------------------------------------------------- -->
				<div class="w3-col dnone" id="avtbox">
					<label class="w3-col m3 w3-right-align w3-padding" for="avt">아바타 선택 : </label>
					<div class="w3-col m9">
						<div class="w3-row w3-padding w3-center dnone" id="mavt">
							<div class="avtfr">
								<input type="radio" name="avt" value="1">
								<div class="w3-col">
									<div class="w3-col w3-center pd5 imgbox w3-border">
										<img src="../img/img_avatar1.png">
									</div>
								</div>
							</div>
							<div class="avtfr">
								<input type="radio" name="avt" value="2">
								<div class="w3-col">
									<div class="w3-col w3-center pd5 imgbox w3-border">
										<img src="../img/img_avatar2.png">
									</div>
								</div>
							</div>
							<div class="avtfr">
								<input type="radio" name="avt" value="3">
								<div class="w3-col">
									<div class="w3-col w3-center pd5 imgbox w3-border">
										<img src="../img/img_avatar3.png">
									</div>
								</div>
							</div>
						</div>
						<div class="w3-row w3-padding w3-center dnone" id="favt">
							<div class="avtfr">
								<input type="radio" name="avt" value="4">
								<div class="w3-col">
									<div class="w3-col w3-center pd5 imgbox w3-border">
										<img src="../img/img_avatar4.png">
									</div>
								</div>
							</div>
							<div class="avtfr">
								<input type="radio" name="avt" value="5">
								<div class="w3-col">
									<div class="w3-col w3-center pd5 imgbox w3-border">
										<img src="../img/img_avatar5.png">
									</div>
								</div>
							</div>
							<div class="avtfr">
								<input type="radio" name="avt" value="6">
								<div class="w3-col">
									<div class="w3-col w3-center pd5 imgbox w3-border">
										<img src="../img/img_avatar6.png">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- ----------------------------------------------------------------------- -->
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="birth">생
						년 월 일 : </label>
					<div class="w3-col m9 w3-padding">
						<input class="w3-col" type="date" id="birth" name="birth">
					</div>
				</div>
			</div>
			<div class="w3-col w3-margin-top">
				<div class="w3-half w3-button w3-red w3-hover-amber">홈으로...</div>
				<div class="w3-half w3-button w3-blue w3-hover-aqua">회원가입</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../js/join.js"></script>
</html>
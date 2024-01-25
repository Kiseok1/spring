<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Profile reply</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='twitterprofile.css'>
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
    
     
    
</head>

<body>

		<%@ include file="/WEB-INF/views/sidebar.jsp" %>
   		<%@ include file="/WEB-INF/views/profile/main.jsp" %>
        	<section class="tweets">
               <div class="heading">
                   <div class="heading_content">게시물</div>
                   <div class="heading_reply">답글<div style="width: 56px; height: 4px; background: #BA68C8; margin: auto;"></div></div>
                   <div class="heading_media">미디어</div>
                   <div class="heading_like">마음에 들어요</div>
               </div>
           	</section>
            <section class="mytweets">
                <div><img src="https://res.cloudinary.com/dowrygm9b/image/upload/v1570267399/laptop-3174729_yiprzu.jpg" alt="avi" class="avi"></div>
                <div class="tweetbody">
                    <div>Soy Segun @segun_OS - Feb. 11</div>
                    <div class="tweetcontent">Always check the replies for clarification before you retweet a tweet,
                        people<br> deliberately mislead with their tweets.</div>
                    <ul class="retweeticons">
                        <i class="fa fa-comment" aria-hidden="true"></i>
                        <i class="fa fa-retweet" aria-hidden="true"></i>
                        <i class="fa fa-loveseat"></i>
                        <i class="fa fa-upload" aria-hidden="true"></i>
                    </ul>
                </div>

            </section>
        </div>
        </div>
       

        

    <!-- <script>
alert('MY TWITTER PROFILE CLONE');
</script> -->

 





</body>

</html>
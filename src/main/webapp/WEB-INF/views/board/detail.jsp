<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
    <br/><br/>

    <div>
        <h3>${board.title}</h3>
        <div class="text-right">
                <span><i>작성자 ${board.user.username}<i></span>
        </div>
    </div>

    <hr/>
    <div>
      <div>${board.content}</div>
    </div>
    <hr/>

    <div class="mx-auto text-center">
        글 번호 <span id="id" class="text-muted"><i>${board.id}<i></span>
    </div>

    <br/><br/>

    <div class="text-right">
    <button class="btn btn-secondary" onclick="history.back();">Back</button>
    <c:if test="${board.user.id == principal.user.id}">
        <a href="/board/${board.id}/updateForm" class="btn btn-warning">Modify</a>
        <button id="btn-delete" class="btn btn-danger">Delete</button>
    </c:if>
    </div>
</div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
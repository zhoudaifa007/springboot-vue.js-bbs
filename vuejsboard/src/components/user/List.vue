<template>
  <div class="container">
    <div class="row">
      <h1>회원 목록</h1>
    </div>
    <div class="row">
      <table>
        <thead>
        <tr>
          <th>아이디</th>
          <th>이름</th>
          <th>이메일</th>
        </tr>
        </thead>
        <tbody>
        <tr data-th-each="data : ${list}">
          <td th:text="${data.userId}"></td>
          <td th:text="${data.username}"></td>
          <td th:text="${data.email}"></td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<template>
  <div class="container">
    <div class="row">
      <h1>회원 정보 수정</h1>
    </div>
    <form method="post" th:action="|/user/${detail.id}?${_csrf.parameterName}=${_csrf.token}|">
      <input type="hidden" name="_method" value="put">
      <div class="row">
        <table>
          <thead>
          <tr>
            <th>아이디</th>
            <th>비밀번호</th>
            <th>이름</th>
            <th>이메일</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td>
              <input type="hidden" name="id" th:value="${detail.id}">
              <input type="text" name="userId" th:value="${detail.userId}" disabled>
            </td>
            <td>
              <input type="password" name="password">
            </td>
            <td>
              <input type="text" name="username" th:value="${detail.username}">
            </td>
            <td>
              <input type="email" name="email" th:value="${detail.email}">
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      <div class="row center">
        <button class="btn" type="submit">정보 수정</button>
      </div>
    </form>
  </div>
</template>

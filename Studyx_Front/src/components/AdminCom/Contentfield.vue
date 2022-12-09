<template>
    <div class="contentfield">
      <div class="card">
        <div class="card-body">
          <slot></slot>
        </div>
      </div>
    </div>
  </template>

  <script>
  export default {};
  </script>

  <style scoped>
  .contentfield{
      padding: 50px;
      border-radius: 40px;
      background-color:aliceblue;
  }
  </style>

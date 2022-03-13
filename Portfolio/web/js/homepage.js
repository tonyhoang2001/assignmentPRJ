/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const username = document.getElementById("user-name")
const down = document.getElementById("down")
const right = document.getElementById("right")
const logout = document.getElementById("logout")
let countCLick = 0

username.addEventListener("click", function () {
    countCLick += 1
    console.log(countCLick)
    if (countCLick % 2 != 0) {
        down.style.display = "none"
        right.style.display = "inline-block"
        logout.style.display = "inline-block"
    }else{
        down.style.display = "inline-block"
        right.style.display = "none"
        logout.style.display = "none"
    }
})


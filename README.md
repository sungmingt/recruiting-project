
# ποΈ κΈ°μ μ±μ© μΉ μλΉμ€ API

<br>

μν°λμ μ§λ μ±μ©μ°κ³ μ½μ€ κ³Όμ λ₯Ό κ°μΈνμ΅ κ²Έ μνν΄λ³Έ λ΄μ©μλλ€. <br>
λͺμλ μκ΅¬μ¬ν­λ€μ ν λλ‘ APIλ₯Ό κ΅¬ν μλ£νκ³ , μ΄ν μ¬κ±΄μ΄ λλ€λ©΄ μμ±λλ₯Ό λμ¬κ° μκ°μλλ€.

<br>

## μκ΅¬μ¬ν­

### API

- μ±μ©κ³΅κ³  λ±λ‘
- μμ 
- μ­μ 
- μ‘°ν (μ μ²΄, κ°λ³)
- κ²μ
- μ§μ

<br>

### κΈ°ν μ¬ν­
- νμ domain : νμ¬, μ¬μ©μ, μ±μ©κ³΅κ³ , μ§μλ΄μ­(μ νμ¬ν­)
- νμ¬, μ¬μ©μ λ±λ‘ μ μ°¨λ μλ΅ (DBμ μμλ‘ μμ±νμ¬ μ§ν)
- λ‘κ·ΈμΈ λ± μ¬μ©μ μΈμ¦μ μ°¨(ν ν° λ±)λ μλ΅
- μ¬μ©μλ μ±μ©κ³΅κ³ μ 1νλ§ μ§μ κ°λ₯νλ€


<br><br><br>


## π μ€ν λ°©λ² (h2)

```c
./gradlew clean bootRun
```
- μ νλ¦¬μΌμ΄μ μ€ν μ ```data.sql``` μΏΌλ¦¬ μ€ν -> λ°λ‘ μ±μ© API μμ²­ κ°λ₯ 

- h2 μ μ νμΈ
http://localhost:8080/h2


<details>
<summary><h3>Http Request Example</h3></summary>
<div markdown="1">

<br>

<h4>μ±μ© κ³΅κ³  λ±λ‘</h4>

```http request
POST http://localhost:8080/recruit
Content-Type : application/json

{
    "companyId" : 1,
    "position" : "λ°±μλ",
    "reward" : 150000,
    "content" : "λ°±μλ java κ°λ°μ μ±μ©",
    "tool" : "java, spring, mysql"
}
```
</div>
</details>


<br><br>



## π ERD

<img width="558" alt="recruit erd μ΅μ’" src="https://user-images.githubusercontent.com/95558880/197238493-a0198727-68fe-48dd-947f-22dfda8d2cd0.png">

μκ΅¬μ¬ν­ : **μ¬μ©μλ 1νλ§ μ§μ κ°λ₯νλ€** -> νμκ³Ό μ±μ©κ³΅κ³  N:1 κ΄κ³

<br><br>


## π API Docs (Swagger)

http://localhost:8080/swagger-ui/index.html#/

<img width="1085" alt="recruiting api" src="https://user-images.githubusercontent.com/95558880/197230360-ea06bcb0-ab91-4f8e-bb96-dead1f4d90cd.png">

<br><br>

## π νμ€νΈ

### slice test + dynamic test

<img width="353" alt="happy 2" src="https://user-images.githubusercontent.com/95558880/197231062-85bbc00a-4b29-4935-8809-dcec9bda51a7.png">


<br>




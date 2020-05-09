# Java

[TOC]

## Glosario

**Objeto** - tienen estados (eventos) y  comportamientos (funciones)

- Usa mayusc. ej: HolaComoEstas

**Clase** - un template que describe el estado y comportamiento del objeto 

**Metodo** - un comportamiento, una funcion

- Usa camelcase. ej: holaComoEstas



## Comentarios

```java
/* aca se pueden meter comentarios multilinea
mientras este todo adento */

//comentario de una linea
```



## Hello World

Every line of code that runs in Java must be inside a `class`.

The `main()` method is required and you will see it in every Java program:

```java
public class MyClass {
  public static void main(String[] args) {
    System.out.println("Hello World");
  }
}

```



## Variables

#### Declarar variables

```java
type variable = value;
//ejemplo
String name = "John";
int myNum = 15;
```

#### Shorthand

Para asignar variables mas rapido

```java
//ejemplo
int x = 5, y = 6, z = 50;
```



## Data types

#### Primitives

| Data Type | Size    | Description                                                  |
| :-------- | :------ | :----------------------------------------------------------- |
| byte      | 1 byte  | Stores whole numbers from -128 to 127                        |
| short     | 2 bytes | Stores whole numbers from -32,768 to 32,767                  |
| int       | 4 bytes | Stores whole numbers from -2,147,483,648 to 2,147,483,647    |
| long      | 8 bytes | Stores whole numbers from -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807 |
| float     | 4 bytes | Stores fractional numbers. Sufficient for storing 6 to 7 decimal digits |
| double    | 8 bytes | Stores fractional numbers. Sufficient for storing 15 decimal digits |
| boolean   | 1 bit   | Stores true or false values                                  |
| char      | 2 bytes | Stores a single character/letter or ASCII values             |

#### Non primitives/Objects

Tienen metodos y usan mayuscula

- Strings
- Arrays
- Classes
- Interface

#### Type casting

Cuando queres cambiar de tipo de variable a una de mayor tamaño tenes que hacer esto

```java
int myInt = 9;
double myDouble = myInt; // Automatic casting: int to double
```

Cuando queres cambiar de tipo de variable a una de menor tamaño tenes que hacer esto

```java
double myDouble = 9.78;
int myInt = (int) myDouble; // Manual casting: double to int
```



## Operators

### Arithmetic

| Operator | Name           | Description                            | Example |
| -------- | -------------- | -------------------------------------- | ------- |
| +        | Addition       | Adds together two values               | x + y   |
| -        | Subtraction    | Subtracts one value from another       | x - y   |
| *        | Multiplication | Multiplies two values                  | x * y   |
| /        | Division       | Divides one value by another           | x / y   |
| %        | Modulus        | Returns the division remainder         | x % y   |
| ++       | Increment      | Increases the value of a variable by 1 | ++x     |
| --       | Decrement      | Decreases the value of a variable by 1 | --x     |

### Assignment operators

| Operator | Example | Same As    |
| :------- | :------ | :--------- |
| =        | x = 5   | x = 5      |
| +=       | x += 3  | x = x + 3  |
| -=       | x -= 3  | x = x - 3  |
| *=       | x *= 3  | x = x * 3  |
| /=       | x /= 3  | x = x / 3  |
| %=       | x %= 3  | x = x % 3  |
| &=       | x &= 3  | x = x & 3  |
| \|=      | x \|= 3 | x = x \| 3 |
| ^=       | x ^= 3  | x = x ^ 3  |
| >>=      | x >>= 3 | x = x >> 3 |
| <<=      | x <<= 3 | x = x << 3 |

### Comparison operators

| Operator | Name                     | Example |
| :------- | :----------------------- | :------ |
| ==       | Equal to                 | x == y  |
| !=       | Not equal                | x != y  |
| >        | Greater than             | x > y   |
| <        | Less than                | x < y   |
| >=       | Greater than or equal to | x >= y  |
| <=       | Less than or equal to    | x <= y  |

### Logical operators

| Operator | Name        | Description                                             | Example            |
| :------- | :---------- | :------------------------------------------------------ | :----------------- |
| &&       | Logical and | Returns true if both statements are true                | x < 5 &&  x < 10   |
| \|\|     | Logical or  | Returns true if one of the statements is true           | x < 5 \|\| x < 4   |
| !        | Logical not | Reverse the result, returns false if the result is true | !(x < 5 && x < 10) |


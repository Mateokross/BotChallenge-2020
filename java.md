# Java

[TOC]

https://www.w3schools.com/java/

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

### Declarar variables

```java
type variable = value;
//ejemplo
String name = "John";
int myNum = 15;
```

### Shorthand

Para asignar variables mas rapido

```java
//ejemplo
int x = 5, y = 6, z = 50;
```



## Data types

### Primitives

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

### Non primitives/Objects

Tienen metodos y usan mayuscula

- Strings
- Arrays
- Classes
- Interface

### Type casting

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



## Strings

### Methods

- toUpperCase()
- toLowerCase()
- indexOf('substring')

### Concatenation

Se usa el `+`

```java
//examples
String firstName = "John";
String lastName = "Doe";
System.out.println(firstName + " " + lastName);
```

### Special characters

Caracteres como `''` y `""` la cagan porque los strings se definen usando estos. Para usarlo dentro de un string tenes q usar `\` antes

```java
//ejemplo
String culo = "culo y quiero usar \"esto\" "
    
//si lo imprimiera saldria esto:
// culo y quiero usar "esto"
```



## Math

Es un objeto con muchos metodos piola

- max(x, y) - encuentra el valor mas alto

- min(x, y) - encuentra el valor mas bajo

- abs(x) - valor absoluto

- sqrt(x) - raiz cuadrada

- random() - numero random entre 1 y 0

  

## if and else statements

```java
if (condition1) {
  // block of code to be executed if condition1 is true
} else if (condition2) {
  // block of code to be executed if the condition1 is false and condition2 is true
} else {
  // block of code to be executed if the condition1 is false and condition2 is false
}
```

### Shorthand

```java
variable = (condition) ? expressionTrue :  expressionFalse;
```



## Switch

```java
switch(expression) {
  case x:
    // code block
    break;
  case y:
    // code block
    break;
  default:
    // code block
    // The default keyword specifies some code to run if there is no case match
}

//example
int day = 4;
switch (day) {
  case 1:
    System.out.println("Monday");
    break;
  case 2:
    System.out.println("Tuesday");
    break;
  case 3:
    System.out.println("Wednesday");
    break;
  case 4:
    System.out.println("Thursday");
    break;
  case 5:
    System.out.println("Friday");
    break;
  case 6:
    System.out.println("Saturday");
    break;
  case 7:
    System.out.println("Sunday");
    break;
}

```



## While loop

```java
while (condition) {
  // code block to be executed
}
//example
int i = 0;
while (i < 5) {
  System.out.println(i);
  i++;
}

```

### Do while

Corre el codigo una vez antes de checkear la condicion

```java
do {
  // code block to be executed
}
while (condition);
```



## For loop

```java
for (statement 1; statement 2; statement 3) {
  // code block to be executed
 }
//example
for (int i = 0; i < 5; i++) {
  System.out.println(i);
}
```

### For each

```java
for (type variableName : arrayName) {
  // code block to be executed
}
//example
String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
for (String i : cars) {
  System.out.println(i);
}
```



## Break and continue

The `break` statement can be used to jump out of a **loop**.

The `continue` statement breaks one iteration (in the loop), if a specified condition occurs, and continues with the next iteration in the loop.

```java
//example
for (int i = 0; i < 10; i++) {
  if (i == 4) {
    break;
  }
  if (i == 5) {
    continue;
  }
  System.out.println(i);
}
```



## Arrays

### Definition

```java
type[] name = {}
//examples
String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
int[] myNum = {10, 20, 30, 40};
```

### Access elements

```java
name[x]
//example
cars[0]
```

### Edit elements

```java
name[x] = x
//example
cars[0] = "Opel";
```

### Get length

Se usa la propiedad `length`

```java
array.length
//example
cars.length
```

### Loop

```java
for (type variable : arrayname) {
  ...
}
//example
String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
for (String i : cars) {
  System.out.println(i);
}
```

### Multidimensional arrays

```java
//example
int[][] myNumbers = { {1, 2, 3, 4}, {5, 6, 7} };
//getting variable
myNumbers[1][2] 
```



## Methods

A **method** is a block of code which only runs when it is called. You can pass data, known as parameters, into a method.

A method must be declared within a class. It is defined with the name of the method, followed by parentheses **()**

```java
//example
public class MyClass {
  static void myMethod() {
    // code to be executed
  }
}
```

- `myMethod()` is the name of the method
- `static` means that the method belongs to the MyClass class and not an object of the MyClass class. 
- `void` means that this method does not have a return value.

### Call a Method

```java
public class MyClass {
  static void myMethod() {
    System.out.println("I just got executed!");
  }

  public static void main(String[] args) { //esto se usa para correr codigo
    myMethod(); //corre el metodo
  }
}
```

### Parameters

```java
//example
static void myMethod(String fname, int age) {
	System.out.println(fname + " is " + age);
}
public static void main(String[] args) {
    myMethod("Liam", 5);
    myMethod("Jenny", 8);
    myMethod("Anja", 31);
}

// Liam is 5
// Jenny is 8
// Anja is 31
```

### Return values

If you want the method to return a value, you can use a primitive data type (such as `int`, `char`, etc.) instead of `void`, and use the `return` keyword inside the method:

```java
static int myMethod(int x) {
    return 5 + x;
}
```

### Method Overloading

With **method overloading**, multiple methods can have the same name with different parameters

```java
//example
int myMethod(int x)
float myMethod(float x)
double myMethod(double x, double y)
```

```java
//example
static int plusMethod(int x, int y) {
  return x + y;
}

static double plusMethod(double x, double y) {
  return x + y;
}

public static void main(String[] args) {
  int myNum1 = plusMethod(8, 5);
  double myNum2 = plusMethod(4.3, 6.26);
  System.out.println("int: " + myNum1);
  System.out.println("double: " + myNum2);
}
```

### Static or Public

Static methods can be called without creating objects while public methods must be called by creating objects.

```java
public class MyClass {
  // Static method
  static void myStaticMethod() {
    System.out.println("Static methods can be called without creating objects");
  }

  // Public method
  public void myPublicMethod() {
    System.out.println("Public methods must be called by creating objects");
  }

  // Main method
  public static void main(String[] args) {
    myStaticMethod(); // Call the static method
    // myPublicMethod(); This would compile an error

    MyClass myObj = new MyClass(); // Create an object of MyClass
    myObj.myPublicMethod(); // Call the public method on the object
  }
}
```



## Intro to OOP

Classes and objects are the two main aspects of object-oriented programming. A class is a template for objects, and an object is an instance of a class.

When the individual objects are created, they inherit all the variables and methods from the class.



## Classes

To create a class, use the keyword `class`:

```java
//example
public class MyClass {
  int x = 5;
}
```

A class should always start with an uppercase first letter, and that the name of the java file should match the class name.

## Objects

An object is created from and inside a class.

```java
public class MyClass {
  int x = 5;

  public static void main(String[] args) {
    MyClass myObj = new MyClass(); //new object
    System.out.println(myObj.x); // prints 5 because it inherited the class properties
  }
}
```

You can create multiple objects of one class



## Using Multiple Classes

You can also create an object of a class and access it in another class. This is often used for better organization of classes (one class has all the attributes and methods, while the other class holds the `main()` method (code to be executed)).

For this you would need to create multiple files in the same folder

#### MyClass.java

```java
public class MyClass {
  int x = 5;
}
```

#### OtherClass.java

```java
class OtherClass {
  public static void main(String[] args) {
    MyClass myObj = new MyClass();
    System.out.println(myObj.x);
  }
}
```



## Class Attributes

The class called "`MyClass`" has two attributes: `x` and `y`

```java
public class MyClass {
  int x = 5;
  int y = 3;
}
```

You can access attributes by creating an object of the class, and by using the dot syntax (`.`):

```java
MyClass myObj = new MyClass();
System.out.println(myObj.x);
```

### Multiple objects

If you create multiple objects of one class, you can change the attribute values in one object, without affecting the attribute values in the other

```java
public class MyClass {
  int x = 5;

  public static void main(String[] args) {
    MyClass myObj1 = new MyClass();  // Object 1
    MyClass myObj2 = new MyClass();  // Object 2
    myObj2.x = 25;
    System.out.println(myObj1.x);  // Outputs 5
    System.out.println(myObj2.x);  // Outputs 25
  }
}
```


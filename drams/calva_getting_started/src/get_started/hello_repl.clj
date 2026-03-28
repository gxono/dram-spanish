(ns get-started.hello-repl)

"¡Bienvenido al REPL de Introducción! 💜"

;; == Se requieren algunos conocimientos de VS Code ==
;; Este tutorial asume que conoces algunas cosas sobre
;; VS Code. Por favor, visita esta página si eres nuevo
;; en el editor: https://code.visualstudio.com/docs

;; == Notación de atajos de teclado usada en este tutorial ==
;; Usamos una notación para los atajos de teclado, donde
;; `+` significa que las teclas se presionan al mismo tiempo
;; y ` ` separa cualquier pulsación de tecla en la secuencia.
;; `Ctrl+Alt/Option+C Enter` significa presionar
;; Ctrl, Alt (u Option) y C
;; todos al mismo tiempo, luego soltar las teclas y
;; luego presionar Enter. (La tecla Alt se llama Option u
;; Opt, en algunas computadoras)
;; Cuando un botón es diferente en distintas plataformas,
;; escribiremos los botones separados por una barra.

;; == Resultados de la evaluación ==
;; Evaluar código mostrará la primera línea de los resultados
;; en línea. El resultado completo se muestra en dos lugares:
;; 1. La "terminal" de salida de Calva, que encontrarás
;;    en la vista Terminal de VS Code.
;; 2. El Inspector de Calva, que es un panel lateral
;;    que te permite inspeccionar el resultado con más detalle.

;; Este proyecto está configurado para iniciar automáticamente
;; el REPL y conectarlo al editor (Jack-in)
;; Si esto no ocurre, puede que estés usando una versión
;; antigua de Calva. Por favor, actualiza a la última
;; versión (o al menos a v2.0.460).
;;
;; Una vez que el REPL esté conectado (el botón REPL en la
;; barra de estado se vuelve ámbar), debes cargar este archivo
;; en el REPL. Usa el comando:
;;   Calva: Load/Evaluate Current File and its Requires/Dependencies
;; Luego puedes evaluar el código ;; en la línea de arriba
;; que dice "¡Bienvenido al REPL de Introducción! 💜"
;; colocando el cursor en la línea y presionando
;; `Alt/Option+Enter`.

;; ¿Lo hiciste? ¡Genial!
;; ¿Ves ese `=> "Bienvenido ...` al final de la línea?
;; Ese es el resultado de la evaluación que acabas de
;; realizar. ¡Acabas de usar el REPL de Clojure!
;; 🎉 ¡Felicitaciones! 🎂
;; Puedes cerrar el resultado en línea presionando `Esc`.

(comment
  ;; Puedes evaluar la cadena de texto a continuación de la misma manera

  "Hello World!"

  ;; Estás en un 'Rich Comment Form', que es donde
  ;; los clojuristas solemos desarrollar nuevo código.
  ;; https://calva.io/rich-comments/
  ;; A menudo se abrevia como RCF.

  ;; Evalúa también la siguiente forma (puedes
  ;; colocar el cursor en cualquier lugar de cualquiera de las dos líneas):

  (repeat 7
          "I am using the REPL! 💪")

  ;; Solo se muestra `=> ("I am using the REPL! 💪"`
  ;; en línea. Puedes ver el resultado completo en tres lugares:
  ;; 1. La "terminal" de salida de Calva
  ;; 2. El Inspector de Calva. Tiene un botón _Inspect_
  ;;    para cada resultado que te permite expandir el resultado
  ;;    y explorarlo como una estructura de árbol.
  ;; 3. En la parte inferior del resultado en línea al pasar el cursor

  ;; Entremos de verdad al modo REPL. 😂
  ;; Coloca el cursor en cualquiera de las cinco líneas de código a continuación:
  ;; `Alt+Enter`, luego `Ctrl/Cmd+K Ctrl/Cmd+I`.

  (map (fn [s]
         (if (< (count s) 5)
           (str "Give me " s "! ~•~ " (last s) "!")
           s))
       ["an R" "an E" "a  P" "an L" "What do you get?" "REPL!"])

  ;; Borra la pantalla en línea con `Esc`. Los resultados
  ;; en línea también se borran cuando editas el archivo.

  ;; Lo que nos lleva a algo MUY IMPORTANTE:
  ;; Por defecto, Calva será un Guardián de los Paréntesis.
  ;; Esto significa que los botones de retroceso y eliminar
  ;; no eliminarán corchetes balanceados. Por favor, intenta
  ;; eliminar un corchete en la expresión de arriba.
  ;; ¿Lo ves?

  ;; PARA ELIMINAR UN CORCHETE BALANCEADO:
  ;;   presiona `alt/option+backspace` o `alt/option+delete`

  :rcf) ; <- Esta es una forma conveniente de evitar que el cierre
        ;    del paréntesis de un Rich comment form se pliegue
        ;    cuando el código está formateado.



;;
;; = CÓMO FUNCIONA ESTA GUÍA =
;;

;; Hay tres archivos para explorar. Para
;; todos los nuevos en Calva:
;; 1. hello_repl.clj (este archivo)
;; 2. welcome_to_clojure.clj (una Guía de Clojure para Principiantes)
;; 3. hello_paredit.clj (introducción a la edición estructural de Calva)

;; Por favor, no te preocupes si no entiendes el código
;; en este archivo y en el archivo de paredit.
;; Son cortos y están hechos para que puedas simplemente seguir
;; el camino sin que el código real tenga sentido todavía. Las cosas
;; se aclararán cuando profundices en la Guía de Clojure.

;; Sobre comandos y atajos:
;; Por favor, lee https://calva.io/finding-commands/
;; (Es muy corto.)
;; Cuando nos referimos a los comandos por su nombre, usa
;; la Paleta de Comandos de VS Code para buscarlos
;; si no conoces el atajo de teclado.
;; Todos los comandos de Calva tienen el prefijo "Calva".

;; == Evaluando definiciones ==
;; Alt+Enter es el atajo de teclado predeterminado de Calva
;; para evaluar las formas del "nivel superior" actuales. Nivel
;; superior significa el "contenedor" más externo de las formas,
;; que es el archivo. Esta definición de función está en
;; el nivel superior. ¡Por favor, evalúala!

(defn greet
  "I'll greet you"
  [s]
  (str "Hello " s "!"))

;; Las formas dentro de `(comment ...)` también se consideran
;; de nivel superior. Esto facilita experimentar
;; con el código.

(comment
  (greet "World")
  :rcf)

;; Cualquier cosa impresa en stdout no se muestra en línea.

(comment
  (println (greet "World"))
  :rcf)

;; Deberías ver el resultado de la evaluación, nil,
;; en línea, y "Hello World!" seguido del resultado
;; impreso en la terminal de salida.

;; ¿Quizás te preguntas qué es una "forma"? Definida de forma general,
;; es más o menos lo mismo que una S-expresión:
;; https://en.wikipedia.org/wiki/S-expression
;; Es decir, ya sea una "palabra" o algo encerrado en
;; algún tipo de corchetes, paréntesis (), corchetes duros [],
;; llaves {}, o comillas "". Todo esto es una
;; forma:

(str 23 (apply + [2 3]) (:foo {:foo "foo"}))

;; También lo son `str`, `23`, "foo", `(apply + [2 3])`,
;; `{:foo "foo"}`, `+`, `[2 3]`, `apply`, y también
;; `(:foo {:foo "foo"})`.

;; Calva tiene un concepto de "forma actual", para permitirte
;; evaluar formas que no están en el nivel superior. La
;; "forma actual" se determina por la posición del cursor.
;; Calva tiene dos comandos que te permitirán fácilmente
;; experimentar con qué forma se considera actual:
;; * Calva: Select Current Form
;; * Calva: Expand Selection


;; == Evaluando la Forma Actual ==
;; Ctrl+Enter evalúa la forma "actual"
;; Pruébalo con el cursor en diferentes lugares en este
;; fragmento de código:

(comment

  (str 23 (apply + [2 3]) (:foo {:foo "foo"}))

  ;; Puede que descubras que Calva considera las palabras en
  ;; cadenas como formas. No entres en pánico si `foo` causa
  ;; un error de evaluación. No está definida, ya que
  ;; no debería estarlo. Puedes definirla, por supuesto,
  ;; solo por diversión y aprendizaje: Evalúa estas
  ;; definiciones en el nivel superior. (`Alt/Option+Enter`)

  (def foo
    [1 2 "three four"])
  (def three 3)
  (def four "four")

  :rcf)

;; Luego evalúa la forma actual dentro de las cadenas de arriba.
;; Lo que le pidas a Calva que envíe al REPL, Calva
;; lo enviará al REPL.


;; == Soporte de Rich Comments ==
;; Repitiendo un concepto importante: Las formas dentro de
;; `(comment ...)` también se consideran de nivel superior
;; por Calva. Alt/Option+Enter en diferentes lugares a continuación
;; para familiarizarte con ello.

(comment
  "I ♥️ Clojure"

  (greet "World")

  foo

  (range 10)

  ;; https://calva.io/rich-comments/
  :rcf)


;; También prueba los comandos *Show Hover*,
;; *Show Definition Preview Hover*
;; *Go to Definition*

(comment
  (println (greet "side effect"))
  (+ (* 2 2)
     2)

  ;; Aquí también, si tienes fuentes de Java instaladas
  (Math/abs -1)
  :rcf)


;; == Tú Controlas lo que se Evalúa ==
;; Ten en cuenta que Calva nunca evalúa tu código
;; a menos que lo pidas explícitamente. Así que, excepto para
;; este archivo, tendrás que cargar los archivos que abras
;; tú mismo. Haz un hábito de esto, porque
;; a veces las cosas no funcionan, y fallan de
;; maneras peculiares, cuando tu archivo no está cargado.

;; Pruébalo con este archivo: `Ctrl+Alt+C Enter`.
;; El resultado de cargar un archivo es cualquier
;; última forma de nivel superior en el archivo.

;; == Editando Código ==
;; Una nota sobre la edición de Clojure en Calva:
;; Si editas y experimentas con los ejemplos
;; notarás que Calva sangra automáticamente tu código.
;; Puedes re-sangrar y formatear el código cuando quieras, usando
;; la tecla `Tab`. Formateará la forma envolvente actual.
;; Pruébalo en los lugares numerados en este fragmento
;; de código, comenzando en `; 1`:

(comment ; 3
  (defn- divisible
    "Is `n` divisible by `d`?"
    [n d]
    (zero? (mod n d)))

  (defn fizz-buzz [n] ; 2
    (cond ; 1
      (divisible n (* 5 3)) "FizzBuzz"
      (divisible n 5)       "Buzz"
      (divisible n 3)       "Fizz"
      :else                  n))
  :rcf)

;; === El modo `strict` de Paredit está activado ===
;; Calva admite la edición estructural (edición que
;; considera formas en lugar de líneas) usando un sistema
;; llamado Paredit. Por defecto, Paredit intenta proteger
;; contra la eliminación accidental de corchetes y el
;; desequilibrio de la estructura de las formas. Para anular la protección,
;; usa `Alt+Backspace` o `Alt+delete`.

(comment
  (defn strict-greet
    "Try to remove brackets and string quotes
   using Backspace or Delete. Try the same
   with the Alt key pressed."
    [name]
    (str "Strictly yours, " name "!"))

  (strict-greet "dear Paredit fan")
  :rcf)

;; (Restaura con *Undo* si es necesario.)
;; Consulta `hello_paredit.clj` para más información. Y también:
;; https://calva.io/paredit

;;;;;;;;;;;;;;;;;;; PUNTO DE CONTROL ;;;;;;;;;;;;;;;;;;;

;; Ahora sabes suficiente sobre Calva para experimentar
;; con el código en `welcome_to_clojure.clj`.
;; Este archivo continúa con algunas características más interesantes de Calva,
;; así que definitivamente deberías regresar aquí
;; después de aprender algunas cosas sobre Clojure.
;; Como, qué es una expresión encadenada (threaded).

;; == Evaluando en Expresiones Encadenadas
;; Ctrl+Alt+Enter evaluará la forma envolvente actual
;; hasta el cursor. Útil en expresiones encadenadas.
;; Digamos que quieres calcular la calificación promedio
;; de los datos a continuación:

(comment
  ;; Primero necesitas evaluar esto en el nivel superior
  (def colt-express
    {:name "Colt Express"
     :categories ["Family"
                  "Strategy"]
     :play-time 40
     :ratings {:pez 5.0
               :kat 5.0
               :wiw 5.0
               :vig 3.0
               :rex 5.0
               :lun 4.0}})

  ;; Y esto
  (defn average [coll]
    (/ (apply + coll) (count coll)))

  ;; También esto, si quieres
  (->> colt-express
       :ratings
       vals
       average)

  ;; Para ver el resultado en cada paso del encadenamiento
  ;; También puedes usar Ctrl+Alt+Enter después de cada forma.
  ;; Coloca el cursor después de `(->> colt-express` y
  ;; pruébalo. Luego después de `:ratings`, y después de `vals`.
  :rcf)

;; == Evaluando la Forma de Nivel Superior hasta el Cursor
;; Shift+Alt+Enter evaluará todo el código desde
;; el inicio de la forma de nivel superior actual, hasta
;; el cursor, con todos los corchetes abiertos cerrados.
;; Pruébalo repitiendo el ejemplo anterior, pero comienza
;; colocando el cursor en, digamos, justo detrás
;; de `:wiw 5.0`, luego evalúa el nivel superior hasta el cursor.

(comment
  ;; El comando es útil al evaluar un bloque de
  ;; código hasta un punto. Por ejemplo, puedes envolver
  ;; algún código en un `(do ...)` y luego usar el
  ;; comando *Evaluate From Start of Top Level Form to Cursor*
  ;; en diferentes lugares para examinar el código.
  ;; Pruébalo en la línea de comentario numerada a continuación.

  ; Pero primero evalúa esto en el nivel superior
  (defn average [coll]
    (/ (apply + coll) (count coll)))

  (do
    (def bar-express
      {:name "Bar Express"
       :categories ["Family"
                    "Strategy"]
       :play-time 40
       :ratings {:pez 5.0
                 :kat 5.0
                 :wiw 5.0   ; 1, luego evalúa `bar-express`
                 :vig 3.0
                 :rex 5.0
                 :lun 4.0}})

    (let [foo-express (-> bar-express
                          (assoc :name "Foo Express")
                          (assoc-in [:ratings :lyr] 5.0)
                          (update-in [:ratings :vig] inc))]
      (->> foo-express   ; 2
           :ratings      ; 3
           vals          ; 4
           (average)     ; 5 (Si esto falla necesitas
                         ;   evaluar en nivel superior la definición
                         ;   de la función `average`.)
           )))
  :rcf)

;; == El Depurador de Calva ==
;; https://calva.io/debugger/
;; La forma más sencilla de usarlo es
;; instrumentar una función para depuración. Para hacer eso
;; coloca el cursor en la función y luego
;; usa el comando:
;; *Instrument Current Top Level Form for Debugging*
;; Luego llama a la función. Esto hará que el
;; depurador se detenga en el primer punto interrumpible en
;; la función instrumentada

(comment
  ;; Intentémoslo primero sin instrumentación. Esta
  ;; función tiene un error. Evalúala de la manera usual
  ;; (`Alt+Enter`) primero y luego llámala.

  (defn bar
    [n]
    (cond (> n 40) (+ n 20)
          (> n 20) (- (first n) 20)
          :else 0))

  (bar 2)  ; funciona
  (bar 24) ; lanza error, ¿qué está pasando?

  ;; Ese es un mensaje de error extraño (quizás digas,
  ;; dependiendo de cuán familiarizado estés con Clojure).
  ;; Ahora instrumenta la función como se describe arriba.
  ;; Calva indicará el código que está instrumentado para
  ;; depuración. Ahora evalúa la llamada a la función problemática.
  ;; El depurador iniciará y esperará a que
  ;; recorras la función paso a paso.
  ;;
  ;; Para des-instrumentar la función, simplemente evalúala
  ;; de la manera normal (evaluación de nivel superior).
  ;; Documentación del depurador aquí: https://calva.io/debugger/

  ;; NB: Si eres nuevo en Clojure, puede que encuentres cierta
  ;; familiaridad al notar que Calva tiene un depurador.
  ;; Sin embargo, intenta explorar la Programación Interactiva,
  ;; usando el REPL primero. Esa es la Manera Clojure.
  ;; Esta sección está aquí para que sepas que
  ;; el depurador existe, para esas raras ocasiones
  ;; en que realmente se necesita.
  :rcf)

;; == Detener Bucles Infinitos ==
;; Como evaluar expresiones de Clojure es tan
;; fácil y divertido, a veces sucede que evalúas
;; algo que nunca termina, o tarda demasiado
;; en terminar. Para esto, Calva tiene un comando llamado
;; *Interrupt Running Evaluations*. Lo necesitarás
;; si evalúas esto en el nivel superior:

(comment
  (def tmp1 (dorun (range)))
  :rcf)

;; ¿Listo? Genial. Por favor, considera familiarizarte
;; más con Paredit usando la guía interactiva.
;; Si eres nuevo en Clojure, por favor
;; continúa con `welcome_to_clojure.clj`, que es una
;; guía interactiva del lenguaje Clojure.

;; ¿Tienes un proyecto Clojure al que quieres conectar
;; Calva? Consulta: https://calva.io/connect/

;; Aprende mucho más sobre Calva en https://calva.io

;; Esta cadena es la última expresión en este archivo
"hello_repl.clj está cargado, y listo con algunas cosas para que pruebes."
;; Es lo que verás impreso en la ventana de salida
;; cuando cargues el archivo.

;; Esta guía fue descargada de:
;; https://github.com/BetterThanTomorrow/dram
;; Por favor, considera contribuir.

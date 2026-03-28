(ns get-started.welcome-to-clojure
  (:require [clojure.repl :refer [source apropos dir pst doc find-doc]]
            [clojure.string :as string]
            [clojure.test :refer [is are]]))

;; ¡Bienvenido a Clojure! ♥️

;; Comienza cargando este archivo.
;; Ctrl+Alt+C Enter
;; (La tecla Alt a veces se llama Option u Opt)

;; Luego evalúa esta expresión con Alt+Enter:

"Hello World"

;; Eso es un Hello World conciso para cualquier lenguaje.
;; Y nota que no hay paréntesis. 😀

;; Esta guía intentará darte una comprensión básica
;; del lenguaje Clojure. Básica en
;; el sentido de que no es extensa. Básica en el
;; sentido de que es fundamental, construyendo desde los primeros
;; principios para hacer el viaje por Clojure
;; que tienes por delante más fácil de comprender.

;; Con los fundamentos en su lugar tendrás una buena
;; oportunidad de tener la intuición correcta sobre cómo
;; programar algo, cómo formular tus preguntas,
;; cómo buscar información de manera efectiva, cómo entender
;; el código con el que te encuentras, y así sucesivamente.

;; Habrá enlaces aquí y allá, ctrl/cmd-clic
;; en ellos para abrirlos en un navegador. Aquí está el primero
;; de esos enlaces;
;; https://clojure.org/guides/learn/syntax
;; Allí puedes leer más sobre los conceptos
;; mencionados en esta guía.

;; La forma de usar la guía es leer sobre los
;; conceptos y evaluar los ejemplos. A veces
;; habrá ejercicios en el texto. No limites tu
;; práctica a esos, sin embargo. Por favor, siéntete animado
;; a editar los ejemplos, y agregar nuevo código
;; y evaluarlo. Evalúa esto para calentar:

(comment
  (str "Welcome"
       " to "
       "Clojure!"
       " "
       "♥️"))

;; Luego mira qué pasa si agregas algunos números
;; aquí y allá y evalúas de nuevo.

;; NB: Esto es un trabajo en progreso...
;; Cuando crees el proyecto REPL de Introducción la
;; próxima vez, puede que haya sido actualizado. 😀
;; También siempre puedes encontrar la última versión aquí:
;; https://github.com/BetterThanTomorrow/dram/blob/dev/drams/calva_getting_started/src/get_started/welcome_to_clojure.clj

(comment
  ;; = EXPRESIONES =
  ;; En Clojure todo es una expresión.
  ;; (No hay declaraciones.) A menos que haya
  ;; un error al evaluar una expresión, siempre
  ;; hay un valor de retorno (que a veces es `nil`).

  ;; Un aspecto importante de esto es que el resultado
  ;; de una expresión es siempre la última forma/expresión
  ;; evaluada. Por ejemplo, si tienes una función definida
  ;; así:

  (defn last-eval-wins []
    (println 'side-effect-1)
    1
    (println 'side-effect-2)
    2)

  ;; Esto define una función llamada
  ;; `last-eval-wins`, que no toma argumentos, con cuatro
  ;; expresiones en su cuerpo. (Volveremos a
  ;; definir funciones más adelante.)

  ;; Llamar a la función

  (last-eval-wins) ; <- Evalúa eso 😄

  ;; causará que las cuatro expresiones en el cuerpo de la función
  ;; se evalúen. El resultado de la llamada será
  ;; la última expresión que fue evaluada.

  ;; En la ventana de salida también verás las
  ;; llamadas a `println` ocurrir. También son
  ;; expresiones, que se evalúan a `nil`.

  (println 'prints-this-evaluates-to-nil)

  ;; Las expresiones están compuestas de literales (que se evalúan
  ;; a sí mismos) y/o llamadas a:
  ;; * formas especiales
  ;; * macros
  ;; * funciones

  ;; "Hello World" al principio de esta guía es una
  ;; cadena literal (por lo tanto, se evalúa a sí misma).
  ;; Más sobre literales en la próxima sección.

  ;; Las llamadas se escriben como listas con la cosa llamada
  ;; como primer elemento.

  (def foo "foo") ; Llama a la forma especial `def`,
                  ; se evalúa al var que crea
                  ; (Más sobre esto más adelante)

  (for [x '(1 2 3)  ; Llama a la macro `for`
        y '(:a :b)] ; (Comprensión de lista)
    [x y])

  (str 1 2 3) ; Llama a la función `str` con los
              ; argumentos 1, 2 y 3.

  ;; Solo las funciones pueden pasarse como valores

  (map str [:foo :bar])    ; funciona
  (map for [:foo :bar])    ; no se puede tomar el valor de una macro
  (map def [:foo :bar])    ; def ni siquiera es un símbolo

  )

(comment
  ;; = LITERALES =
  ;; Los literales se evalúan a sí mismos.
  ;; (Recuerda a tus amigos:
  ;;   Alt+Enter y Ctrl+Enter)

  ;; Tipos numéricos
  18        ; entero
  -1.8      ; punto flotante
  0.18e2    ; exponente
  18.0M     ; decimal grande
  18/324    ; proporción
  18N       ; entero grande
  0x12      ; hexadecimal
  022       ; octal
  2r10010   ; base 2

  ;; Tipos de caracteres
  "hello"         ; cadena
  \e              ; carácter
  #"[0-9]+"       ; expresión regular

  ;; Símbolos e identificadores
  map             ; símbolo
  +               ; símbolo - la mayoría de los signos de puntuación permitidos
  clojure.core/+  ; símbolo con espacio de nombres
  nil             ; valor nulo/nil (nombrado en la tradición LISP)
  true false      ; booleanos
  :alpha          ; keyword
  :release/alpha  ; keyword con espacio de nombres
  ::alpha         ; keyword con espacio de nombres,
                  ; en el espacio de nombres actual

  ;; == KEYWORDS ==
  ;; Los keywords comienzan con `:`. Son una cosa
  ;; en sí mismos, usados frecuentemente como identificadores y como
  ;; claves en mapas (más sobre mapas más adelante). Los keywords son
  ;; muy eficientes en memoria y velocidad.

  ;; El mismo keyword es por supuesto igual a sí mismo

  (= :foo :foo)

  ;; Sin embargo, también es idéntico a sí mismo

  (identical? :foo :foo)

  ;; Esto significa que es la misma cosa, ocupando el
  ;; mismo lugar (muy pequeño) en memoria.
  ;; Incluso si construyes un keyword no literal
  ;; sigue siendo idéntico a su forma literal

  (identical? (keyword "foo") :foo)

  ;; Esto es verdad para todo tu programa Clojure.
  ;; Los keywords son globales. Existe una sintaxis de espacio de nombres
  ;; para ellos, para que puedas tener control de esto.

  ;; Los keywords también son funciones, en realidad. Pero más
  ;; sobre eso más adelante. Por ahora basta decir
  ;; que los keywords tienen un papel muy especial e importante
  ;; en la mayoría de los programas Clojure.

  ;; == CADENAS ==
  ;; En algún lugar entre los literales atómicos y
  ;; las colecciones tenemos las cadenas. A veces se
  ;; tratan como secuencias (una abstracción genial sobre la que
  ;; hablaré más).
  ;; Las cadenas están encerradas por comillas dobles.

  "A string can be
   multi-line, but will contain any leading spaces."
  "Write strings
like this, if leading spaces are no-no."

  ;; (La comilla simple se usa para otra cosa.
  ;; Verás para qué un poco más adelante.)
  )

;; = CONSEJOS: Herramientas para practicar Clojure =
;;
;; Aquí hay dos recursos que funcionan muy bien juntos
;; con esta guía:
;;
;; Rich4Clojure: Practica Clojure con problemas 4Clojure en VS Code
;; * https://github.com/PEZ/Rich4Clojure
;; Aprendiendo Clojure usando Koans con VS Code y Calva
;; * https://github.com/DanBunea/clojure-koans
;;
;; Ambos te permiten aprender y practicar Clojure resolviendo
;; problemas, conectado al REPL, en el editor, como
;; esta guía. Una forma de usarlos es abrirlos en
;; ventanas separadas de VS Code e ir y venir entre
;; ellos y esta guía, a medida que aprendes cosas, y
;; ves la necesidad de aprender más.

(comment
  ;; = ESPACIOS DE NOMBRES =
  ;; Por más importantes que son los espacios de nombres, no nos detendremos mucho
  ;; en el tema en esta guía. La documentación oficial
  ;; les hace la mejor justicia:
  ;; https://clojure.org/reference/namespaces
  ;;
  ;; Hay algunas cosas que realmente necesitamos saber
  ;; sin embargo...
  ;; Los símbolos de Clojure se definen en espacios de nombres (con
  ;; la forma especial `def`) donde son accesibles
  ;; desde cualquier otro espacio de nombres.

  (def foo-2 "foo")

  ;; También debes saber que existe algo llamado el
  ;; espacio de nombres actual. (Un poco como el directorio de trabajo
  ;; actual en el shell.) Cuando evaluaste la
  ;; forma `def` anterior, viste dónde quedó definido `foo-2`.

  ;; Al evaluar un símbolo desde cualquier espacio de nombres
  ;; debe haber sido definido, o el compilador
  ;; se quejará y lanzará un error

  foo-3

  ;; El espacio de nombres también necesita haber sido creado

  some-namespace/foo

  ;; Si has cargado el archivo `hello_repl.clj`
  ;; el espacio de nombres `hello-repl` se crea y sus
  ;; símbolos de nivel superior están definidos.

  get-started.hello-repl/greet

  (get-started.hello-repl/greet "from the welcome-to-clojure namespace")

  ;; Si estos lanzan errores, primero necesitas cargar
  ;; `hello_repl.clj`, o al menos evaluar su forma `ns`
  ;; y la forma `greet`.

  ;; No es recomendable depender de que algún
  ;; espacio de nombres exista así. Eso hace
  ;; tu código frágil. Es mejor `require`
  ;; el espacio de nombres. Si no lo has cargado, puedes
  ;; hacerlo al mismo tiempo:

  (require 'get-started.hello-paredit :reload)

  get-started.hello-paredit/strict-greet
  (get-started.hello-paredit/strict-greet "World")

  ;; Para la mayoría del código Clojure que escribes organizarás
  ;; el código en archivos separados con un espacio de nombres cada uno,
  ;; y usarás la forma `ns` (que comienza la mayoría de los
  ;; archivos Clojure) para `:require` los espacios de nombres necesarios, dandoles
  ;; alias a algo conveniente y a veces `:refer`
  ;; algunos de sus símbolos para que puedas usarlos
  ;; sin el prefijo del espacio de nombres (que es el
  ;; texto antes del `/`, por cierto, en caso de que no fuera
  ;; suficientemente obvio). Examina la forma `ns` de este
  ;; archivo para ver por qué estas formas compilan sin
  ;; quejas:

  (doc require) ; Revisa la ventana de salida
  (string/split "foo:bar:baz" #":")

  ;; Ver también:
  ;; https://clojuredocs.org/clojure.core/ns

  ;; Cualquier espacio de nombres puede crearse en el REPL. Sin embargo,
  ;; cuando se requiere un espacio de nombres, ya sea a través de
  ;; las funciones `require` o `use`, o a través de la forma `ns`,
  ;; el Lector de Clojure buscará el archivo indicado
  ;; por el espacio de nombres requerido en el classpath. Al
  ;; hacerlo, los puntos en el nombre del espacio de nombres separan
  ;; directorios, y los guiones serán reemplazados por guiones bajos.
  ;; Digamos que tienes un `src` en tu classpath, y un archivo
  ;; `src/foo/bar_baz.clj` en el proyecto. Este archivo debería
  ;; tener una forma `ns` como esta:

  ;(ns foo.bar-baz ,,,)

  ;; Y lo requieres usando algo como:

  ;(require 'foo.bar-baz)

  ;; O:

  ;(ns welcome-to-clojure
  ;  (:require [foo.bar-baz]))

  ;; Si evalúas cualquiera de esos requires, obtendrás un
  ;; mensaje de error del repl, diciéndote qué archivos buscó
  ;; el Lector de Clojure para encontrar la definición del espacio de nombres.

  ;; Dos errores comunes:
  ;; 1. Nombrar archivos usando guiones en lugar de guiones bajos.
  ;; 2. Usar `(require ...)` en lugar de `(:require)` en la
  ;;    forma `ns`.

  ;; La forma `ns` tiene mucha funcionalidad y puede ser un poco
  ;; complicada de entender. Aquí hay una hoja de trucos:
  ;; https://gist.github.com/ghoseb/287710/


  ;; === Keywords con espacio de nombres ===
  ;; Los keywords también pueden tener espacio de nombres, pero no
  ;; están realmente registrados en un espacio de nombres, como
  ;; los símbolos, por lo que puedes simplemente usarlos, independientemente

  :foo-whatever
  :whatever-namespace/foo

  ;; La noción sobre el espacio de nombres actual existe
  ;; para los keywords en que el prefijo de doble dos puntos
  ;; se expande a `:<espacio-de-nombres-actual>/foo`:

  ::foo

  ;; Es importante saberlo. `:foo` se
  ;; referirá al mismo keyword independientemente de desde qué
  ;; espacio de nombres se use. `::foo` no.
  )

(comment
  ;; = COLECCIONES =
  ;; Clojure tiene sintaxis literal para cuatro tipos de colección
  ;; Se evalúan a sí mismos.

  '(1 2 3)     ; lista (una lista citada, más sobre esto abajo)
  [1 2 3]      ; vector
  #{1 2 3}     ; conjunto
  {:a 1 :b 2}  ; mapa

  ;; Se componen

  {:foo [1 2]
   :bar #{1 2}}

  ;; En Clojure hacemos la mayoría de las cosas con solo estas
  ;; colecciones. Colecciones literales y funciones.
  )

(comment
  ;; = FUNCIONES =
  ;; Hasta ahora has podido evaluar todos los ejemplos.
  ;; Es porque citamos esa lista.
  ;; En realidad las listas se ven así

  (1 2 3)

  ;; Pero si evalúas eso, obtendrás un error:
  ;; => class java.lang.Long cannot be cast to class
  ;;    clojure.lang.IFn
  ;; (Por supuesto, el linter ya te advirtió.)
  ;; Al evaluar listas sin citar, el primer elemento
  ;; de la lista se considera en "posición de función".
  ;; Eso significa que Clojure intentará llamar a `1`
  ;; como una función, lo que no funcionará porque no
  ;; es una función.
  ;; Puede que estés empezando a sospechar que un programa Clojure
  ;; es solo datos, ¿verdad? Lo cual es correcto. El código Clojure
  ;; es datos. Más sofisticado, Clojure es homoicónico:
  ;; https://wiki.c2.com/?HomoiconicLanguages
  ;; Esto otorga gran poder macro, más sobre eso abajo.

  ;; Aquí hay algunas listas con funciones apropiadas en
  ;; la posición 1:

  (str 1 2 3 4 5 :foo)
  (< 1 2 3 4 5)
  (*)
  (= "1"
     (str "1")
     (str \1))
  (println "From Clojure with ♥️")
  (reverse [5 4 3 2 1])

  ;; Todo después de la primera posición se
  ;; pasa a la función como argumentos

  ;; Nota: Me referiré a literales, símbolos, listas,
  ;; y otras colecciones literales, colectivamente como formas,
  ;; a veces, sexprs:
  ;; https://en.wikipedia.org/wiki/S-expression
  ;; Arriba, `(str 1 2 3 4 5 :foo)` es una forma, al igual que `str`,
  ;; `1` y `:foo`.


  ;; Defines nuevas funciones y las vinvulas a nombres
  ;; en el espacio de nombres actual usando la macro `defn`.
  ;; Es una macro muy flexible. Aquí hay un uso simple:

  (defn add2
    [arg]
    (+ arg 2))

  ;; Define la función `add2` que toma un argumento.
  ;; El cuerpo de la función llama a las funciones principales `+`
  ;; con los argumentos `arg` y 2.
  ;; Evaluar la forma la definirá y verás:
  ;; => #'welcome-to-clojure/add2
  ;; Ese es un var que "contiene" el valor de la función
  ;; Ahora puedes referenciar el var usando el símbolo
  ;; `add2`. Poniéndolo en la posición de función de una
  ;; lista con 3 en la primera posición de argumento y
  ;; evaluando la lista, ¿qué obtenemos?

  (add2 3)

  ;; Clojure tiene una extensa biblioteca de funciones principales
  ;; y macros. Ver: https://clojuredocs.org para un
  ;; motor de búsqueda de Clojure core (y más) impulsado por la comunidad.
  )

(comment
  ;; = FORMAS ESPECIALES y MACROS =
  ;; La biblioteca principal está compuesta de las funciones y macros
  ;; de la propia biblioteca. Bootstrapping de la biblioteca está
  ;; en unas pocas (alrededor de 15) formas primitivas incorporadas,
  ;; también conocidas como "formas especiales".

  ;; Ya has conocido una de estas formas especiales:

  (quote (1 2 3))

  ;; El doc hover del símbolo `quote` te dice que
  ;; es una forma especial.

  ;; ¿Te preguntas dónde conociste esta forma especial antes?
  ;; Usé la sintaxis abreviada para ello entonces:

  '(1 2 3)

  ;; Convéncete de que son iguales con la función `=`:

  (= (quote (1 2 3))
     '(1 2 3))

  ;; Clojure tiene semántica de valores. Cualquier estructura de datos
  ;; que se evalúe a los mismos datos es igual,
  ;; sin importar cuán profunda o grande sea la estructura.

  (= [1 [1 #{1 {:a 1 :b '(:foo bar)}}]]
     [1 [1 #{1 {:a (- 3 2) :b (quote (:foo bar))}}]])

  ;; ... pero ese fue un desvío, volvamos a las formas especiales.
  ;; Documentación oficial:
  ;; https://clojure.org/reference/special_forms#_other_special_forms

  ;; Una forma especial muy importante es `fn` (que en
  ;; realidad son cuatro formas especiales, pero de todas formas).
  ;; Sin esta forma no podemos definir nuevas funciones.
  ;; La siguiente forma se evalúa a una función que
  ;; suma 2 a su argumento.

  (fn [arg] (+ arg 2))

  ;; Llamar a la función con el argumento 3:

  ((fn [arg] (+ arg 2)) 3)

  ;; Otra forma especial es `def`. Define cosas,
  ;; dándoles nombres con espacio de nombres.

  (def foo :foo)

  ;; "Definir una cosa" significa que se crea un var,
  ;; que contiene el valor, y que un símbolo queda vinculado
  ;; al var. Evaluar el símbolo recoge el
  ;; valor del var al que está vinculado.

  foo

  ;; Se puede acceder al var usando la forma especial `var`.

  (var foo)

  ;; La mayoría de las veces verás la abreviatura var-quote

  #'foo

  ;; Con estas dos formas especiales podemos definir funciones

  (def add2-2 (fn [arg] (+ arg 2)))
  (add2-2 3)

  ;; Esto es lo que hace la macro `defn`. La mayoría de las veces
  ;; definirás funciones como lo vimos
  ;; antes, (al discutir la posición de función de
  ;; una forma):

  (defn add2-3
    [arg]
    (+ arg 2))

  ;; Podemos usar la función `macroexpand` para ver qué
  ;; produce la macro:

  (macroexpand '(defn add2-3
                  [arg]
                  (+ arg 2)))

  ;; Otra forma especial sumamente importante:

  (if 'test
    'value-if-true
    'value-if-false)

  ;; `macroexpand` no hace nada aquí, ya que `if` no es
  ;; una macro:

  (macroexpand '(if test
                  value-if-true
                  value-if-false))

  ;; (Tampoco es una función)

  ;; Dato curioso: Además de `case`, todos los condicionales y
  ;; constructos de flujo de control en Clojure se construyen usando `if`:

  (macroexpand '(when test
                  value-if-true))

  (macroexpand '(or a b))

  (require 'clojure.walk) ;; necesitarás evaluar esto

  (clojure.walk/macroexpand-all '(or a b)) ;; para evaluar esto

  (macroexpand '(cond
                  y value-if-y
                  z value-if-z
                  :else value-if-x-neither-y-nor-z))

  (clojure.walk/macroexpand-all '(cond
                                   y value-if-y
                                   z value-if-z
                                   :else value-if-x-neither-y-nor-z))

  ;; Un lenguaje de programación necesita sus condicionales. Pero
  ;; en el núcleo, Clojure casi se las arregla solo con `if`.
  ;; Casi. Como mencioné antes, `case` es una excepción.
  ;; Al menos en la JVM, donde Clojure usa tablas de salto para
  ;; implementarlo:

  (macroexpand '(case x
                  y value-if-x-is-y
                  z value-if-x-is-z
                  value-if-x-is-neither-y-nor-z))


  ;; Volveremos a `if` y los condicionales.

  ;; == `let` ==
  ;; `let` es una forma especial que te permite vincular valores a
  ;; variables que se usarán en el cuerpo de la forma.

  (let [x 1
        y 2]
    (str x y))

  ;; Los vínculos se proporcionan como el primer "argumento",
  ;; en un vector. Este es un patrón que usan
  ;; otras formas especiales y macros que te permiten definir
  ;; vínculos. Es similar al ámbito léxico de otros
  ;; lenguajes de programación (aunque esto es más bien
  ;; estructural). Las formas hermanas y padres no
  ;; "ven" estos vínculos (no tienen manera de hacerlo

  (do
    (def x :namespace-x)
    (println "`x` in `do` _before_ `let`: " x)
    (let [x :let-x]
      (println "`x` from `let`: " x))
    (println "`x` in `do`, _after_ `let`: " x))

  (println "`x` _outside_ `do`: " x)

  ;; Como se mencionó antes en esta guía, la forma especial `def`
  ;; define cosas "globalmente", aunque con espacio de nombres.

  ;; Si has seguido las instrucciones para examinar
  ;; cosas mencionadas aquí, por ejemplo haciendo
  ;; ctrl/cmd-clic en el símbolo `let` en los fragmentos
  ;; de código, encontrarás que en `core.clj`, `let`
  ;; está definido como una macro. No importa eso. Se
  ;; hace referencia a él como una forma especial aquí
  ;; https://clojure.org/reference/special_forms#let

  ;; Cerremos (juego de palabras no intencionado) la sección de formas especiales
  ;; notando que junto con _cómo_ Clojure
  ;; lee y evalúa el código, las formas especiales forman
  ;; el propio lenguaje Clojure. El siguiente nivel de
  ;; bloques de construcción son las macros.

  ;; Pero primero investiguemos esto sobre cómo se
  ;; lee el código...
  )

(comment
  ;; = EL LECTOR =
  ;; https://clojure.org/reference/reader
  ;; El Lector de Clojure es responsable de leer texto,
  ;; haciendo datos de él, que es lo que recibe el compilador.
  ;; El Lector es donde los literales, símbolos, cadenas, listas,
  ;; vectores, mapas y conjuntos se descomponen y
  ;; vuelven a ensamblar, determinando qué es una función,
  ;; una macro o una forma especial.

  ;; Al hacer esto, el espacio en blanco juega un papel clave y
  ;; también hay algunas reglas de sintaxis adicionales en juego.

  ;; == ESPACIO EN BLANCO ==
  ;; La mayoría de las cosas que pensarías que cuentan como espacio en blanco
  ;; son espacio en blanco, y además Clojure,
  ;; siendo un LISP, no necesita comas para separar
  ;; los elementos de la lista. Sin embargo, se pueden usar comas para esto
  ;; de todas formas, ya que las comas son espacio en blanco.

  (= '(1 2 3)
     '(1,2,3)
     '(1, 2, 3)
     '(1,,,,2,,,,3))

  ;; (No hay operadores en Clojure, `=` es una
  ;; función. Verificará la igualdad de todos
  ;; los argumentos que se le pasen.)

  ;; == COMENTARIOS DE LÍNEA ==
  ;; El Lector omite leer todo en una línea desde
  ;; un punto y coma. Este es un comentario no estructurado
  ;; en el sentido de que si empiezas una forma

  (range 1 ; 10)
  ;; y luego colocas un comentario de línea de manera que el corchete
  ;; de cierre de esa forma quede comentado, la
  ;; estructura se rompe.
         )
  ;;     ^ Reparando la estructura.

  ;; Si quitas el punto y coma en la forma de apertura
  ;; de arriba, asegúrate de quitar también este paréntesis de cierre.

  ;; Como todo en la línea se ignora, puedes
  ;; agregar tantos puntos y coma como quieras.
  ;;;;;;;;;; (omitido por el Lector)
  ;; Es común usar dos puntos y coma para iniciar un
  ;; comentario de línea completo.

  ;; == SINTAXIS EXTRA ===
  ;; Ya hemos visto la comilla simple

  'something

  ;; Que es, como hemos visto, transformada a

  (quote something)

  ;; `quote` es necesario para evitar que el Lector trate
  ;; las cosas como algo que debería ser evaluado.
  ;; Mira qué pasa si evalúas `something`
  ;; sin las comillas:

  something

  ;; así como la diferencia entre evaluar estos:

  (1 2 3 4)
  '(1 2 3 4)

  ;; Hay algunos más de citas, e incluso de empalme
  ;; de símbolos, que no cubriré en esta guía.

  ;; === Deref ===
  ;; Clojure también tiene tipos de referencia, discutiremos
  ;; (brevemente) el más común, `atom`, más adelante.

  (def an-atom (atom [1 2 3]))
  (type an-atom)

  ;; Para acceder al valor de una referencia:

  (deref an-atom)
  (type (deref an-atom))

  ;; De nuevo, `deref` se usa para desreferenciar muchos
  ;; tipos de referencia diferentes, incluyendo futures,
  ;; https://clojure.org/reference/refs
  ;; https://clojure.org/about/concurrent_programming

  ;; De todas formas, `deref` es tan común que hay
  ;; sintaxis abreviada para ello

  @an-atom
  (= (deref an-atom)
     @an-atom)

  ;; Es un error común olvidar hacer deref

  (first an-atom)
  (first @an-atom)

  ;; === EL DESPACHADOR (SIGNO DE ALMOHADILLA) ===
  ;; Ese signo de almohadilla aparece de vez en cuando. Tiene un
  ;; papel especial. También se conoce como Dispatch.
  ;; Dependiendo de qué carácter lo sigue,
  ;; suceden cosas diferentes e interesantes. Algunas interesantes
  ;; a continuación:

  ;; Las expresiones regulares tienen sintaxis literal, se
  ;; escriben como cadenas, pero con un signo de almohadilla al frente

  #"reg(?:ular )?exp(?:ression)?"

  ;; Las expresiones regulares son manejadas por la plataforma host, así que
  ;; son expresiones regulares Java en este tutorial. Si
  ;; evaluaste la expresión regular anterior, podemos probarla.

  (re-seq *1 "regexp regular expression")

  ;; `*1` es un símbolo especial para una variable que contiene
  ;; el valor del último resultado de evaluación. Puede
  ;; ser más fácil obtener una expresión regular correcta usándola
  ;; directamente:

  (re-seq #"fooo*" "fo foo fooo")
  (re-find #"fooo*" "fo foo fooo")

  ;; Si el signo de almohadilla va seguido de `(`, el Lector
  ;; comenzará a esperar un cuerpo de función.

  #(+ % 2)

  ;; Esta es una sintaxis especial para "literales de función", una
  ;; forma de especificar una función. El ejemplo anterior es
  ;; equivalente a esta función anónima.

  (fn [arg] (+ arg 2))

  ;; Anidar literales de función está prohibido

  ;(#(+ % (#(- % 2) 3)))

  ;; (afortunadamente)

  ;; Además de conjuntos, expresiones regulares y literales de función
  ;; hemos visto var-quotes antes en esta guía

  #'add2

  ;; También hubo una breve discusión sobre los `vars`.
  ;; Puede que quieras revisarla y también leer más
  ;; sobre ello, porque es un concepto muy importante.
  ;; https://clojure.org/reference/vars

  ;; Hay un despachador hash muy útil que
  ;; se usa para hacer que el Lector ignore la siguiente forma

  #_(println "The reader will not send this function call
to the compiler") "This is not ignored"

  ;; Para probar esto selecciona el marcador de ignorar junto con
  ;; la llamada a la función y la cadena, luego usa Alt+Enter,
  ;; para hacer que Calva envíe todo al Lector, que
  ;; lo leerá, ignorará la llamada a la función y solo evaluará
  ;; la cadena.

  ;; Dado que #_ ignora la siguiente forma es un mecanismo de
  ;; comentario estructural, usado frecuentemente para deshabilitar temporalmente
  ;; algún código o algunos datos

  (str "a" "b" #_(str 1 2 3 [4 5 6]) "c")

  ;; Los marcadores de ignorar se acumulan

  (str "a" #_#_"b" (str 1 2 3 [4 5 6]) "c")

  ;; Ten en cuenta que el Lector _sí_ leerá la forma ignorada.
  ;; Si hay errores sintácticos allí, el
  ;; Lector se pondrá triste, se quejará y dejará de leer.
  ;; Selecciona desde el marcador hasta e incluyendo la cadena
  ;; aquí y presiona Ctrl+Enter

  ;#_(#(+ % (#(- % 2) 3))) "foo"

  ;; Dos variantes más comunes de # que verás y usarás
  ;; son la sintaxis abreviada de keyword con espacio de nombres de mapa y
  ;; los literales etiquetados, también conocidos como lectores de datos. Empecemos
  ;; con el primero:

  (= #:foo {:bar 'bar
            :baz 'baz}
     {:foo/bar 'bar
      :foo/baz 'baz})

  ;; No relacionado con #: Hay otra abreviatura para
  ;; especificar keywords con espacio de nombres. Los keywords de doble dos puntos
  ;; reciben el espacio de nombres del espacio de nombres actual

  ::foo
  (= ::foo :calva-getting-started.src.get-started.welcome-to-clojure/foo)

  ;; Los literales etiquetados, entonces. Es una forma de invocar funciones
  ;; vinculadas a las etiquetas en la forma que los sigue.
  ;; https://clojure.org/reference/reader#tagged_literals
  ;; También se conocen como lectores de datos. Puedes
  ;; definir los tuyos propios. Aquí basta con mencionar
  ;; los dos incorporados.

  ;; #inst convertirá la cadena que etiqueta a un instante.
  ;; (Es decir, un momento en el tiempo)

  #inst "2018-03-28T10:48:00.000"
  (type *1)

  ;; #uuid creará un UUID de la cadena que etiqueta

  #uuid "0000000-0000-0000-0000-000000000016"
  (java.util.UUID/fromString "0000000-0000-0000-0000-000000000016")

  ;; Ahora sabes cómo leer (en el sentido de que tú
  ;; eres un Lector de Clojure) la mayor parte del código Clojure.
  ;; Dicho esto, omitamos entrar en la sintaxis
  ;; azucarada y las formas especiales para hacer la interoperabilidad
  ;; con la plataforma host extra agradable.
  ;; https://clojure.org/reference/java_interop
  ;; Solo un vistazo:

  (.before #inst "2018-03-28T10:48:00.000"
           #inst "2021-02-17T00:27:00.000")

  ;; Esto invoca el método `before` en el objeto de fecha
  ;; del año 2018, dándole la fecha del
  ;; año 2021 como argumento. Verás un poco
  ;; más de interoperabilidad con Java en esta guía y probablemente
  ;; notarás cuán disponible está la plataforma host cuando
  ;; programas en Clojure. Lo mismo aplica para
  ;; ClojureScript y para Clojure CLR.

  ;; Repitiendo este recurso importante sobre el Lector:
  ;; https://clojure.org/reference/reader
  ;; Y además de eso, lee sobre Todos Esos
  ;; Caracteres Raros aquí:
  ;; https://clojure.org/guides/weird_characters
  )

(comment
  ;; = MACROS =
  ;; Clojure tiene poderosas capacidades de transformación de datos.
  ;; Tocaremos eso un poco más adelante.
  ;; Aquí quiero destacar que este poder puede
  ;; usarse para extender el propio lenguaje.
  ;; Dado que el código Clojure es estructurado y el código es
  ;; datos, Clojure puede usarse para producir código Clojure
  ;; a partir de código Clojure. Es similar a las
  ;; facilidades del preprocesador que algunos lenguajes
  ;; ofrecen, como el `#pragma` de C, pero es mucho más
  ;; conveniente y poderoso. Mucho de lo que
  ;; aprenderás a amar y reconocer como Clojure
  ;; en realidad se crea con Clojure, como macros.

  ;; Esta guía se preocupa principalmente por hacerte saber
  ;; que las macros son una cosa, para ayudarte a
  ;; reconocer rápidamente cuando estás usando una macro en lugar
  ;; de una función. Es decir, no profundizaré en el
  ;; tema de cómo crear macros.

  ;; La distinción es importante, porque aunque
  ;; las llamadas a macros se parecen mucho a las llamadas a funciones,
  ;; las macros no son de primera clase. No pueden
  ;; pasarse como argumentos, ni devolverse como resultados.
  ;; Más sobre "de primera clase" en la sección sobre
  ;; funciones, más adelante.

  ;; == `when` ==
  ;; Examinemos brevemente la macro `when`.
  ;; Esta macro ayuda a escribir código más legible.
  ;; ¿Cómo? Digamos que quieres evaluar
  ;; algo condicionalmente. Arriba aprendiste que hay
  ;; una forma especial llamada `if` que puede usarse para
  ;; esto. Así:

  (if 'this-is-true
    'evaluate-this
    'else-evaluate-this)

  ;; Ahora digamos que no tienes algo que evaluar
  ;; en el caso else. `if` te permite escribir esto

  (if 'this-is-true
    'evaluate-this)

  ;; Lo cual está bien, pero tendrás que escanear el
  ;; código un poco más para ver que no hay rama else.
  ;; Fácil con este ejemplo corto, pero puede
  ;; volverse bastante complicado en código real. Para abordar esto,
  ;; podrías escribir:

  (if 'this-is-true
    'evaluate-this
    nil)

  ;; Pero eso es un poco tonto, ¿qué pasaría si hubiera una
  ;; forma de decirle al humano que lee el código que
  ;; no hay rama else? ¡La hay!

  (when 'this-is-true
    'evaluate-this)

  ;; Veamos cómo está definido `when`, puedes
  ;; ctrl/cmd-clic en `when` para navegar a donde
  ;; está definido en Clojure `core.clj`.
  ;; También puedes usar la función `macroexpand`

  (macroexpand '(when 'this-is-true
                  'evaluate-this))

  ;; Notarás que `when` envuelve el cuerpo en
  ;; un `(do ...)`, que es una forma especial que te permite
  ;; evaluar varias expresiones, devolviendo los
  ;; resultados del último.
  ;; https://clojuredocs.org/clojure.core/do
  ;; `do` es útil cuando quieres tener algún efecto
  ;; secundario ocurriendo, además de evaluar algo.
  ;; En el desarrollo esto ocurre frecuentemente cuando
  ;; quieres hacer `println` de algo antes del resultado
  ;; de la expresión que se evalúa y devuelve.

  (do (println "The quick brown fox jumps over the lazy dog")
      (+ 2 2))

  ;; La macro `when` te permite aprovechar que
  ;; solo hay una rama, por lo que puedes hacer esto

  (when 'this-is-true
    (println "The quick brown fox jumps over the lazy dog")
    (+ 2 2))

  ;; Sin `when` escribirías:

  (if 'this-is-true
    (do
      (println "The quick brown fox jumps over the lazy dog")
      (+ 2 2)))

  ;; Aquí `when` nos ahorra tanto el escaneo adicional para
  ;; la rama else como el uso de `do`.

  ;; En cuanto a las macros, `when` es de las más simples
  ;; que existen. A partir de dos formas especiales incorporadas,
  ;; `if` y `do`, compone una forma que nos ayuda a
  ;; escribir código fácil de escribir y fácil de leer.

  ;; == `for` ==
  ;; La macro `for` realmente demuestra cómo Clojure
  ;; puede extenderse usando Clojure. Puede que pienses
  ;; que proporciona bucles como el bucle for en muchos
  ;; otros lenguajes, pero en Clojure no hay bucles for.
  ;; En cambio, `for` trata sobre comprensiones de listas
  ;; (si tienes experiencia con Python, sí, ese tipo de
  ;; comprensiones de listas). Así es como producir el
  ;; producto cartesiano de dos vectores, `x` e `y`:

  (for [x [1 2 3]
        y [1 2 3 4]]
    [x y])

  ;; Si recuerdas la forma `let` anterior, y cómo
  ;; te permite vincular variables para usar en el cuerpo de la
  ;; forma, esto es similar, solo que `x` e `y`
  ;; se vincularán a cada valor en las secuencias y el
  ;; cuerpo se evaluará para todas las combinaciones de
  ;; `x` e `y`.

  ;; ¿Todos los valores? Bueno, `for` también te permite filtrar los
  ;; resultados

  (for [x [1 2 3]
        y [1 2 3 4]
        :when (not= x y)]
    [x y])

  ;; Puedes vincular nombres de variables en la comprensión
  ;; para almacenar cálculos intermedios y generalmente
  ;; hacer el código más legible

  (for [x [1 2 3]
        y [1 2 3 4]
        :let [d' (- x y)
              d (Math/abs d')]]
    d)

  ;; Es lo mismo que:

  (for [x [1 2 3]
        y [1 2 3 4]]
    (Math/abs (- x y)))

  ;; Discutible qué es más legible en este caso particular...
  ;; ¯\_(ツ)_/¯

  ;; Una nota sobre el nombre de variable `d'` anterior:
  ;; `d'` es solo un nombre de símbolo como cualquier otro. La
  ;; comilla simple no tiene significado especial a menos que sea
  ;; el primer carácter

  ;; Los filtros y los vínculos pueden usarse juntos.
  ;; Usa tanto `:let` como `:when` para hacer que esta
  ;; comprensión devuelva una lista de todos los `[x y]` donde
  ;; su suma es impar. Las funciones `+` y `odd?`
  ;; son tus amigas aquí.

  (for [x [1 2 3]
        y [1 2 3 4]]
    [x y])

  ;; (Sí, puede resolverse sin `:let` ni `:when`.
  ;; Sígueme la corriente. 😎)

  ;; Consulta https://www.youtube.com/watch?v=5lvV9ICwaMo para
  ;; una excelente introducción a las comprensiones de listas de Clojure
  ;; Consulta https://clojuredocs.org/clojure.core/for para
  ;; ejemplos de uso y consejos.

  ;; Ten en cuenta que aunque `let` y `for` parecen
  ;; funciones, no lo son. Al compilador no
  ;; le gustaría que le pasaras símbolos no definidos a una
  ;; función. Este es código legal:

  (let [abc 1]
    2)

  ;; Este no lo es.

  (str [abc 1]
       1)

  ;; (Nota que el linter clj-kondo está marcando el
  ;; primero con una advertencia y el segundo como un error)
  ;; Las macros extienden el compilador de Clojure.
  ;; https://clojure.org/reference/macros

  ;; == Macros de encadenamiento ==
  ;; Las macros pueden reorganizar totalmente tu código. Las
  ;; macros de "encadenamiento" incorporadas hacen esto. A veces
  ;; cuando el anidamiento de llamadas a funciones (ish) se vuelve
  ;; profundo, puede ser un poco difícil de leer y mantener
  ;; el control de todos los paréntesis

  (Math/abs
   (apply -
          (:d (zipmap
               [:a :b :c :d]
               (partition 2 [1 1 2 3 5 8 13 21])))))

  ;; Lees Clojure desde la expresión más interna
  ;; hacia afuera, lo que se vuelve más fácil con el tiempo, pero
  ;; un programador Clojure experimentado todavía encontraría
  ;; más fácil leer esto

  (->> [1 1 2 3 5 8 13 21]
       (partition 2)
       (zipmap [:a :b :c :d])
       :d
       (apply -)
       (Math/abs))

  ;; Leamos esto juntos. Se usa la macro thread-last,
  ;; `->>`; toma su primer argumento y
  ;; lo coloca (lo encadena) como el último argumento en la
  ;; función siguiente. El primer paso de este modo en
  ;; aislamiento:

  (->> [1 1 2 3 5 8 13 21]
       (partition 2))

  ;; El primer argumento/elemento pasado a `->>`  es
  ;; `[1 1 2 3 5 8 13 21]`
  ;; Se inserta como el último elemento de la
  ;; llamada a la función `(partition 2)`, generando:

  (partition 2 [1 1 2 3 5 8 13 21])

  ;; Esto particiona la lista en listas de
  ;; 2 elementos => `((1 1) (2 3) (5 8) (13 21))`
  ;; Esta nueva lista se inserta luego (encadena)
  ;; como el último argumento a la siguiente función,
  ;; generando:

  (zipmap [:a :b :c :d] '((1 1) (2 3) (5 8) (13 21)))

  ;; Lo cual "comprime" juntos un mapa Clojure usando
  ;; la primera lista como claves y la segunda lista
  ;; como valores
  ;; => `{:a (1 1), :b (2 3), :c (5 8), :d (13 21)}`
  ;; Este mapa se encadena luego como el último argumento
  ;; a la función `:d`

  (:d '{:a (1 1), :b (2 3), :c (5 8), :d (13 21)})

  ;; (En Clojure los keywords son funciones que se buscan
  ;;  a sí mismos en el mapa que se les pasa.)
  ;; => `(13 21)`
  ;; Ya sabes cómo funciona, esto se encadena

  (apply - '(13 21))

  ;; Lo cual aplica la función `-` sobre la lista
  ;; => `-8`
  ;; Luego esto se encadena a `Math/abs`

  (Math/abs -8)
  ;; 🎉

  ;; (En muchos editores capaces de Clojure, incluyendo
  ;; Calva, hay comandos para "desenrollar"
  ;; un encadenamiento, y para convertir expresiones
  ;; anidadas en un encadenamiento. Busca "thread"
  ;; entre los comandos.)
  ;; https://github.com/clojure-emacs/clj-refactor.el/wiki/cljr-unwind-all

  ;; También hay una macro thread-first
  ;; `->` https://clojuredocs.org/clojure.core/-%3E
  ;; A veces no quieres encadenar ni primero
  ;; ni último. También hay una macro para esto.
  ;; `as->` te permite vincular un nombre de variable al
  ;; elemento encadenado y colocarlo donde
  ;; desees en cada llamada a función.

  (as-> 15 foo
    (range 1 foo 3)
    (interpose ":" foo))

  ;; https://clojuredocs.org/clojure.core/as-%3E

  ;; Es común aprovechar el hecho de que la mayoría de los caracteres
  ;; están disponibles al nombrar símbolos Clojure. A menudo
  ;; uso `$` para esta macro de encadenamiento:

  (as-> 15 $
    (range 1 $ 3)
    (interpose ":" $))

  ;; Otros usan otros nombres 😄

  (as-> 15 <>
    (range 1 <> 3)
    (interpose ":" <>))

  ;; Creo que los emojis deberían evitarse, la documentación oficial
  ;; solo menciona alfanuméricos más:
  ;; `*`, `+`, `!`, `-`, `_`, `'`, `?`, `<`, `>`, y `=`
  ;; (así que ni siquiera `$`) pero aquí vamos:

  (as-> 15 ❤️
    (range 1 ❤️ 3)
    (interpose ":" ❤️))

  ;; Otras macros de encadenamiento principales son:
  ;; `cond->`, `cond->>`, `some->`, y `some->>`
  ;; https://clojuredocs.org/clojure.core/cond-%3E

  ;; Por favor, siéntete animado a copiar los ejemplos
  ;; de ClojureDocs aquí y jugar con ellos.
  ;; Aquí hay uno:

  (cond-> 1        ; empezamos con 1
    true inc       ; la condición es true así que (inc 1) => 2
    false (* 42)   ; la condición es false así que la operación se omite
    (= 2 2) (* 3)) ; (= 2 2) es true así que (* 2 3) => 6

  ;; Consulta "Threading with Style" de Stuart Sierra
  ;; para el uso idiomático de las facilidades de encadenamiento.
  ;; https://stuartsierra.com/2018/07/06/threading-with-style
  )

;; Con las formas especiales, la sintaxis especial del Lector,
;; y las macros, se sientan los fundamentos de lo que es el lenguaje Clojure
;; que usas. Por supuesto, puedes extender
;; el lenguaje con bibliotecas que incluyen macros
;; o crear las tuyas. Sin embargo, el lenguaje principal, con
;; sus macros es muy expresivo. Tomar enfoques orientados a datos
;; a menudo es suficiente. Incluso preferible, en lugar
;; de crear más macros.

;; ¡Al flujo de control!

(comment
  ;; = Flujo de Control, Condicionales, Ramificaciones =
  ;; Clojure es más rico que la mayoría de los lenguajes en lo que
  ;; nos ofrece para dejar que nuestros programas fluyan de la manera que
  ;; queremos. Casi todas las características de la biblioteca principal para
  ;; esto se implementan usando la primitiva (forma
  ;; especial) `if`. Esta sigue siendo la base para nosotros como
  ;; programadores Clojure. Toma tres formas como sus
  ;; argumentos:
  ;; 1. Una condición para evaluar
  ;; 2. Qué evaluar si la condición se evalúa
  ;;    a algo verdadero (truthy)
  ;; 3. La forma para evaluar si la condición no
  ;;    se evalúa a algo truthy (la rama "else")
  ;; Lanza este dado unas diez o veinte veces, verificando si
  ;; es un seis:

  (if (= 6 (inc (rand-int 6)))
    "One time out of six you get a six"
    "Five times out of six you get something else")

  ;; Dado que no hay declaraciones en Clojure, `if` es
  ;; el equivalente a la expresión ternaria `if` que
  ;; encuentras en C y muchos otros lenguajes:
  ;;   test ? true-expression : false-expression
  ;; Pseudocódigo para nuestro dado:
  ;;   int(rand() * 6) + 1 == 6 ?
  ;;     "One time out of six you get a six" :
  ;;     "Five times out of six you get something else";

  ;; == La Búsqueda de la Verdad ==
  ;; De nuevo, en Clojure usamos expresiones que se evalúan a
  ;; valores. Cuando se examinan para ramificación, todos los valores
  ;; son ya sea truthy o falsy. De hecho, casi todos
  ;; los valores son truthy

  (if true :truthy :falsy)
  (if :foo :truthy :falsy)
  (if '() :truthy :falsy)
  (if 0 :truthy :falsy)
  (if "" :truthy :falsy)

  ;; Los únicos valores falsy son `false` y `nil`

  (if false :truthy :falsy)
  (if nil :truthy :falsy)
  (when false :truthy)

  ;; Sobre ese último: `when` se evalúa a `nil`
  ;; cuando la condición es falsy. Dado que `nil` es
  ;; falsy, la expresión `when` anterior haría que
  ;; se evaluara la rama "else" de un `if`

  (if (when false :truthy) :true :falsy)

  ;; (Código muy, muy malo, pero de todas formas)
  ;; Cuando solo la verdad o falsedad booleana puede
  ;; servir, existe la función `true?`

  (true? true)
  (true? 0)
  (true? '())
  (true? nil)
  (true? false)

  ;; Por lo tanto

  (if (true? 0) :true :false)

  ;; == `when` ==
  ;; Como se mencionó antes, `when` es un `if` de una sola rama,
  ;; solo para la rama truthy, que está
  ;; envuelta en un `do` por ti. Prueba esto y luego
  ;; prueba reemplazando el `when` con un `if`:

  (when :truthy
    (println "That sounds true to me")
    :truthy-for-you)

  ;; Si la condición `when` no es truthy,
  ;; se devolverá `nil`.

  (when nil :true-enough?)

  ;; == `cond` ==
  ;; Ya que las estructuras if/else profundamente anidadas pueden ser
  ;; difíciles de escribir, leer y mantener, el núcleo de Clojure
  ;; ofrece varios constructos más para el flujo de control,
  ;; uno muy común es la macro `cond`. Toma
  ;; pares de formas condición/resultado, prueba
  ;; cada condición, si es verdadera, entonces la forma resultado
  ;; se evalúa y "devuelve", cortocircuitando
  ;; para que no se pruebe más condición.

  (let [dice-roll (inc (rand-int 6))]
    (cond
      (= 6 dice-roll)  "Six is as high as it gets"
      (odd? dice-roll) (str "An odd roll " dice-roll " is")
      :else            (str "Not six, nor odd, instead: " dice-roll)))

  ;; El `:else` es solo el keyword `:else` que
  ;; se evalúa a sí mismo y es truthy. Es la
  ;; forma convencional de dar a tus formas cond un
  ;; valor predeterminado. Sin una cláusula predeterminada, la
  ;; forma se evaluaría a `nil` para cualquier valor que no sea seis
  ;; ni impar. Pruébalo colocando dos marcadores de ignorar
  ;; (`#_ #_`) frente al keyword `:else`.

  ;; Hay que amar ClojureDocs
  ;; https://clojuredocs.org/clojure.core/cond
  ;; Pega ejemplos de allí aquí y juega:

  ;; Ver también los enlaces a información de `cond->` arriba

  ;; == `case` ==
  ;; Un poco similar a los constructos `switch/case` en
  ;; otros lenguajes, el núcleo de Clojure tiene la macro `case`
  ;; que toma una expresión de prueba, seguida de
  ;; cero o más cláusulas (pares) de constante-de-prueba/expr,
  ;; seguida de una expr opcional. (Sin embargo, el cuerpo
  ;; después de la expresión de prueba no puede estar vacío.)

  (let [test-str "foo bar"]
    (case test-str
      "foo bar" (str "That's very " :foo-bar)
      "baz"     :baz
      (count    test-str)))

  ;; La expresión final, si la hay, se "devuelve" como
  ;; el valor predeterminado.

  (let [test-str "foo bar"]
    (case test-str
      #_#_"foo bar" (str "That's very " :foo-bar)
      "baz"     :baz
      (count    test-str)))

  ;; Si ninguna cláusula coincide y no hay predeterminado,
  ;; ocurre un error en tiempo de ejecución

  (let [test-str "foo bar"]
    (case test-str
      #_#_"foo bar" (str "That's very " :foo-bar)
      "baz"     :baz
      #_(count    test-str)))

  ;; ¡CUIDADO! Una constante de prueba debe ser un literal en tiempo de
  ;; compilación, y el compilador no te ayudará a encontrar
  ;; errores como este:

  (let [test-int 2
        two 2]
    (case test-int
      1     :one
      two   (str "That's not a literal 2")
      (str test-int ": Probably not expected")))

  ;; https://clojuredocs.org/clojure.core/case
  ;; Pega algunos ejemplos de `case` aquí y experimenta

  ;; El podcast Functional Design in Clojure tiene un
  ;; episodio fantástico sobre ramificaciones
  ;; https://clojuredesign.club/episode/089-branching-out/

  ;; == Menos ramificación es bueno, ¿verdad? ==
  ;; La biblioteca principal es rica en funciones que
  ;; te ayudan a evitar escribir código de ramificación. En cambio
  ;; proporcionas la condición como un predicado.
  ;; Una función predicado usada frecuentemente es `filter`

  (filter even? [0 1 2 3 4 5 6 7 8 9 10 11 12])

  ;; y su "hermano" `remove`

  (remove odd? [0 1 2 3 4 5 6 7 8 9 10 11 12])

  ;; Filtrar secuencias de valores es una tarea común
  ;; y tu tiempo de programación puede usarse
  ;; para decidir _cómo_ debe filtrarse, escribiendo
  ;; el predicado. A veces ni siquiera necesitas
  ;; hacerlo, Clojure core es rico en predicados

  (zero? 0)
  (even? 0)
  (neg? 0)
  (pos? 0)
  (nat-int? 0)
  (empty? "")
  (empty? [])
  (empty? (take 0 [1 2 3]))
  (integer? -2/1)
  (indexed? [1 2 3])
  (indexed? '(1 2 3))

  ;; ¿Qué es un predicado? Para los propósitos de esta guía,
  ;; un predicado es una función que prueba cosas para
  ;; su truthiness. Es convención que estas funciones
  ;; terminen con `?`. Muchas toman solo un argumento.

  ;; Un predicado útil es `some?` que prueba
  ;; "algidad", si no es `nil` es
  ;; algo

  (some? nil)
  (some? false)
  (some? '())

  ;; Puedes usarlo para probar si algo es `nil`
  ;; envolviéndolo en una llamada a la función `not`

  (not (some? nil))
  (not (some? false))

  ;; Te dan ganas de definir una función llamada `nil?`,
  ;; ¿verdad? No tienes que hacerlo

  (nil? nil)
  (nil? false)

  ;; Clojure core también contiene predicados que toman
  ;; un predicado más una colección para aplicarlo.
  ;; Como `every?`

  (every? nat-int? [0 1 2])
  (every? nat-int? [-1 0 1 2])

  ;; Revisa los docs de `nat-int?` y crea
  ;; algunas listas más para probar, como

  (every? nat-int? [0 1 2N]) ; 2N no es precisión fija
  (doc nat-int?)

  ;; Este patrón con funciones que toman funciones como
  ;; argumento es común en Clojure. Se extiende más allá de
  ;; los predicados. Las funciones que toman funciones como
  ;; argumentos se denominan funciones de "orden superior".
  ;; https://en.wikipedia.org/wiki/Higher-order_function
  )

(comment
  ;; = Funciones =
  ;; Antes de profundizar en las funciones de orden superior, veamos
  ;; las funciones. Las funciones son ciudadanos de primera clase
  ;; en Clojure y los principales bloques de construcción para
  ;; resolver tus problemas de negocio.

  ;; Hemos visto algunas formas de crear funciones.
  ;; Aquí hay una función anónima que devuelve el
  ;; entero que se le da, a menos que sea divisible por
  ;; 15, en cuyo caso devuelve "fizz buzz".
  ;; (De ninguna manera el problema completo de Fizz Buzz.)

  (fn [n]
    (if (zero? (mod n 15))
      "fizz buzz"
      n))

  ;; Definámosla (vinculémosla a un símbolo que podamos usar)

  (def fizz-buzz-1 (fn [n]
                     (if (zero? (mod n 15))
                       "fizz buzz"
                       n)))
  (fizz-buzz-1 2)
  (fizz-buzz-1 15)

  ;; Hay una macro que nos permite definir y crear
  ;; la función en una sola llamada

  (defn fizz-buzz-2 [n]
    (if (zero? (mod n 15))
      "fizz buzz"
      n))

  (fizz-buzz-2 4)

  ;; `defn` nos permite proporcionar documentación para la
  ;; función

  (defn fizz-buzz-3
    "Says 'fizz buzz' if `n` is divisible by 15,
     otherwise says `n`"
    [n]
    (if (zero? (mod n 15))
      "fizz buzz"
      n))

  (doc fizz-buzz-3) ; (o pasa el cursor sobre `fizz-buzz-3`)

  ;; Es fácil colocar el doc string incorrectamente,
  ;; especialmente ya que es común escribir la forma `defn`
  ;; como lo hicimos con `fizz-buzz-2` arriba.

  (defn fizz-buzz-4
    [n]
    "Says 'fizz buzz' if `n` is divisible by 15,
     otherwise says `n`"
    (if (zero? (mod n 15))
      "fizz buzz"
      n))

  ;; Esto especifica un cuerpo de función completamente válido, así que
  ;; Clojure no se quejará de ello. Pero:

  (doc fizz-buzz-4)

  ;; La configuración predeterminada de clj-kondo te ayudará a
  ;; detectar estos errores. Sin embargo, no puede ayudar con
  ;; esto:

  (defn only-the-last-eval-returns [x]
    [1 x]
    [2 x])

  (only-the-last-eval-returns "foo")

  ;; Es fácil detectarlo así y también preguntarse
  ;; por qué escribirías una función de
  ;; esa manera. Sin embargo, probablemente cometerás este error,
  ;; especialmente si alguna vez escribes Hiccup, que es
  ;; una manera muy buena de escribir HTML con estructuras de datos Clojure.
  ;; Lo usa la popular biblioteca Reagent
  ;; https://purelyfunctional.tv/guide/reagent/#hiccup
  ;; Cuando cometas el error y termines tu búsqueda
  ;; de errores de una hora, escucharás a esta guía susurrar
  ;;   "¡Lo sabía!"

  ;; El vector de vinculación de argumentos de `fn` (y
  ;; por lo tanto `defn`) vincula cada argumento en orden
  ;; a un nombre.

  (defn coords->str [x y]
    (str "x: " x ", y: " y))

  ;; == Funciones Variádicas ==
  ;; Puedes definir funciones que toman un número arbitrario
  ;; de argumentos colocando un `&` frente
  ;; al nombre del último argumento. Eso vincula el nombre
  ;; a una secuencia que contiene todos los argumentos restantes.

  (defn lead+members [lead & members]
    {:lead lead
     :members members})

  (lead+members "Dave Mustain"
                "Marty Friedman"
                "Nick Menza"
                "David Ellefson")

  ;; == Multi-aridad ==
  ;; Clojure admite firmas de funciones basadas en
  ;; el número de argumentos. La macro `defn` te permite
  ;; definir cada aridad como una lista separada. Esto
  ;; se usa frecuentemente para proporcionar valores predeterminados

  (defn hello
    ([] (hello "World"))
    ([s] (str "Hello " s "!")))

  (hello)
  (hello "Clojure Friend")

  ;; O para crear un valor de "identidad" para una función,
  ;; (Un valor de inicio que usa el resto de la operación.)
  ;; Digamos que quieres sumar dos coordenadas x-y

  (defn add-coords-1 [coord-1 coord-2]
    {:x (+ (:x coord-1)
           (:x coord-2))
     :y (+ (:y coord-1)
           (:y coord-2))})

  (add-coords-1 {:x -2 :y 10}
                {:x 4 :y 6})

  ;; ¿Qué pasaría si los requisitos fueran que si la
  ;; función se llama con un argumento debe
  ;; sumarlo al origen? (¿Ves lo que hice allí?
  ;; El valor de identidad es donde debe empezar la función,
  ;; así que empieza desde el origen. 😎)
  ;; Podemos ver que `add-coords-1` falla aquí

  (add-coords-1 {:x -2 :y 10})

  ;; necesitamos agregar una aridad de un argumento

  (defn add-coords-2
    ([coord]
     (add-coords-2 {:x 0
                    :y 0}
                   coord))
    ([coord-1 coord-2]
     {:x (+ (:x coord-1)
            (:x coord-2))
      :y (+ (:y coord-1)
            (:y coord-2))}))

  (add-coords-2 {:x -2 :y 10})

  ;; Ahora si se llama sin argumentos debería
  ;; devolver el origen, porque si no agregas
  ;; ninguna coordenada te quedas en el inicio.
  ;; Escribe una función `add-coords-3` que devuelva
  ;; el origen cuando se llama así

  (add-coords-3)

  ;; Aún debería poder llamarse así

  (add-coords-3 {:x 3 :y 4})
  (add-coords-3 {:x 2 :y 4}
                {:x -4 :y -4})

  ;; Tiene que ver con hacer que la función se componga
  ;; con otras funciones. Por ejemplo, la función `apply`
  ;; que es una función de orden superior que "aplica"
  ;; una función sobre una secuencia. Ahora mismo podemos
  ;; aplicar nuestra función `add-coords-2` así

  (apply add-coords-2 [{:x 1 :y 1} {:x 4 :y 4}])

  ;; Y así

  (apply add-coords-2 [{:x 1 :y 1}])

  ;; Pero no así

  (apply add-coords-2 [])

  ;; Pero la función `add-coords-3` que creaste sí puede

  (apply add-coords-3 [])

  ;; No manejará una secuencia arbitrariamente larga
  ;; de coordenadas, sin embargo. Para eso necesitaríamos una
  ;; aridad más así

  (defn add-coords-4
    ;; agrega la aridad de cero argumentos de tu `add-coords-3` aquí
    ;; agrega la aridad de un argumento de tu `add-coords-3` aquí
    ([coord-1 coord-2]
     {:x (+ (:x coord-1)
            (:x coord-2))
      :y (+ (:y coord-1)
            (:y coord-2))})
    ([coord-1 coord-2 & more-coords]
     ;; Implementa esta aridad cuando hayas aprendido
     ;; sobre la función de orden superior `reduce`
     ))

  (apply add-coords-4 [{:x 1 :y 1}
                       {:x 1 :y 1}
                       {:x 1 :y 1}
                       {:x -6 :y -6}])

  ;; Escucha a Eric Normand explicar con más detalle
  ;; por qué es importante la identidad de una función:
  ;; https://lispcast.com/what-is-a-functions-identity/

  ;; == Cierres ==
  ;; Cuando creas funciones sobre la marcha, lambdas,
  ;; si quieres, usas ya sea la forma especial `fn`
  ;; directamente, o por delegación con la sintaxis `#()`.
  ;; Esto crea un cierre, como lo hace en JavaScript
  ;; y otros lenguajes. Es decir, estas funciones pueden
  ;; acceder a instantáneas de variables con los valores que
  ;; tenían cuando se creó la función

  (defn named-coords-factory [name]
    (fn [x y] {:name name
               :coords {:x x
                        :y y}}))

  (def bob-coords-fn (named-coords-factory "Bob"))
  (def fred-coords-fn (named-coords-factory "Fred"))

  (bob-coords-fn 0 0)
  (fred-coords-fn 5 5)
  (bob-coords-fn 7 7)

  ;; Los cierres son útiles para crear funciones de baja aridad
  ;; dentro de cajas de vinculación let para que las use el cuerpo
  ;; de la función:

  (defn whisper-or-yell-or-ask [command sentence]
    (let [whisper (fn []
                    (str (string/lower-case sentence) command))
          yell (fn []
                 (str (string/upper-case sentence) command))
          ask (fn []
                (str sentence "?"))
          default (fn []
                    (str sentence command " ¯\\_(ツ)_/¯"))]
      (case command
        "" (whisper)
        "!" (yell)
        "?" (ask)
        (default))))

  ;; Todas las funciones creadas en la caja de vinculación let
  ;; "cierran" el `command` y la `sentence` para que
  ;; el `case` pueda mantenerse conciso y legible.

  (whisper-or-yell-or-ask "" "How wOnDerFuLLY NIce To seE")
  (whisper-or-yell-or-ask "!" "Hello tHERE")
  (whisper-or-yell-or-ask "?" "How are you doing")
  (whisper-or-yell-or-ask ":" "Oh well")

  ;; == El Mapa de Atributos ==
  ;; La macro `defn` te permite agregar atributos a la
  ;; función en forma de un mapa. Esto se agrega
  ;; como metadatos (un poco más sobre eso después)
  ;; al var que contiene la función. El mapa va
  ;; después del nombre de la función, y después de cualquier documentación,
  ;; y antes del vector de argumentos (o cualquier aridad)

  (defn i-have-attributes
    {:doc "Docs can be added like this too"
     :foo "Any attributes you fancy"}
    []
    "Good for you")

  (doc i-have-attributes)
  (meta #'i-have-attributes)

  ;; Un atributo útil que puedes agregar es una función de prueba.
  ;; Los ejecutores de pruebas lo detectarán

  (defn fizz-buzz-5
    "That limited fizz-buzz function again"
    {:test (fn []
             (is (= "fizz-buzz" (fizz-buzz-5 15)))
             (is (= 3 (fizz-buzz-5 3))))}
    [n]
    (if (pos? (rem 15 n))
      "fizz-buzz"
      n))

  (clojure.test/test-var #'fizz-buzz-5)
  ;; ¡Ups! Necesitarás corregir los errores. 😀

  ;; ¿Qué tal implementar el Fizz Buzz completo?
  ;; https://en.wikipedia.org/wiki/Fizz_buzz

  (defn fizz-buzz
    "My Fizz Buzz solution"
    {:test (fn []
             (are [arg expected]
                  (= expected (fizz-buzz arg))
               1  1
               3  "Fizz"
               4  4
               5  "Buzz"
               7  7
               15 "Fizz Buzz"
               20 "Buzz"))}
    [n])

  (clojure.test/test-var #'fizz-buzz)
  (map fizz-buzz (range 1 40))

  ;; Los metadatos que tienen significado especial para
  ;; el compilador y varios componentes principales de Clojure
  ;; se listan aquí:
  ;; https://clojure.org/reference/special_forms

  ;; ¡Ahora, a las funciones de orden superior!
  )

(comment
  ;; = Funciones de orden superior =
  ;; Una gran contribución a lo que hace a Clojure un
  ;; lenguaje tan poderoso es que las funciones son
  ;; "de primera clase"
  ;; https://en.wikipedia.org/wiki/First-class_function
  ;; Pueden ser valores en colecciones (también claves
  ;; en mapas) y pueden pasarse como argumentos a otras
  ;; funciones, y "devolverse" como resultados de
  ;; evaluaciones. Puede que estés familiarizado con el
  ;; concepto de lenguajes como JavaScript.

  ;; Veamos algunas funciones de orden superior en
  ;; Clojure core. `some` llama a la función en los
  ;; elementos de su colección, uno por uno, y
  ;; devuelve el primer resultado truthy, y devolverá
  ;; `nil` si la lista se agota antes de que algún elemento
  ;; resulte en algo truthy.

  (some even? [1 1 2 3 5 8 13 21])

  ;; No hay que confundirla con `some?`, que no es
  ;; una función de orden superior.

  (some some? [nil false])
  (some some? [nil nil])

  ;; Un idioma común en Clojure es buscar cosas
  ;; en una colección usando un `set` como predicado.
  ;; Sí, los conjuntos son funciones. Usados como funciones,
  ;; buscarán el argumento dado en
  ;; ellos mismos.

  (#{"foo" "bar"} "bar")

  ;; Por lo tanto

  (some #{"foo"} ["foo" "bar" "baz"])
  (some #{"fubar"} ["foo" "bar" "baz"])

  ;; `apply` toma una función y una colección y
  ;; "aplica" la función en la colección. Digamos que
  ;; tienes una colección de números y quieres sumarlos.
  ;; Esto no funcionará:

  (+ [1 1 2 3 5 8 13 21])

  ;; `apply` al rescate

  (apply + [1 1 2 3 5 8 13 21])

  ;; Concatenar los números como una cadena:

  (apply str [1 1 2 3 5 8 13 21])

  ;; Contrasta con

  (str [1 1 2 3 5 8 13 21])

  ;; También hemos visto `filter` y `remove` arriba, dos
  ;; funciones de orden superior muy comúnmente usadas. Juegan
  ;; en la misma liga que `map` y `reduce`.
  ;; Sigue leyendo. 😎
  )

(comment
  ;; = `map` y `reduce` =
  ;; Entre las funciones de orden superior que puede que hayas
  ;; usado en otros lenguajes con funciones de primera clase
  ;; están `map` y `reduce`. Vale la pena
  ;; estudiarlos y practicarlos con mucho detalle, aquí hay
  ;; un adelanto muy agradable:
  ;; https://purelyfunctional.tv/courses/3-functional-tools/

  ;; Veámoslos también brevemente aquí.
  ;; `map` llama a una función en los elementos de una o
  ;; más colecciones de principio a fin y devuelve una
  ;; secuencia (perezosa, más sobre eso después) de los resultados
  ;; en el mismo orden. Digamos que queremos decrementar
  ;; cada elemento en una lista de números en uno

  (map dec '(1 1 2 3 5 8 13 21))

  ;; Digamos que luego queremos decrementarlos de nuevo

  (->> '(1 1 2 3 5 8 13 21)
       (map dec)
       (map dec))

  ;; Hmm, ¿mejor restar dos, quizás?

  (map (fn [n] (- n 2)) '(1 1 2 3 5 8 13 21))

  ;; Si le das a `map` más colecciones para trabajar
  ;; repetidamente:
  ;; 1. tomará el siguiente elemento de cada colección
  ;; 2. los dará a la función de mapeo como argumentos
  ;; 3. agregará el resultado a su secuencia de retorno
  ;; Hasta que se agote la colección más corta

  (map + [1 2 3] '(0 2 4 6 8))
  (map (fn [n1 s n2] (str n1 ": " s "-" n2))
       (range)
       ["foo" "bar" "baz"]
       (range 2 -1 -1))

  ;; (No hemos hablado mucho de `range`, es una
  ;; función que produce secuencias de números. Sin
  ;; argumentos produce una secuencia infinita, ¡cuidado! 😀,
  ;; de enteros
  ;; 0, 0+1, 0+2, 0+3, 0+4, 0+5, 0.6 ...
  ;; ¡Menos mal que las otras secuencias se agotaron!)

  ;; Muchas de las tareas que podrías resolver con bucles `for`
  ;; en otros lenguajes, se resuelven con `map`
  ;; en Clojure.

  ;; Con otras tareas "de tipo bucle for" usarás
  ;; `reduce`. A diferencia de `map`, no está
  ;; limitado a producir resultados de la misma longitud
  ;; o forma que la colección de entrada. En cambio,
  ;; acumula un resultado de cualquier forma. Por ejemplo,
  ;; puede crear una cadena a partir de una colección de
  ;; números

  (reduce (fn [acc n]
            (str acc ":" n))
          [1 1 2 3 5 8 13 21])

  ;; `reduce` llamará a la función con dos
  ;;  argumentos: el resultado de la última llamada a la función
  ;;  y el siguiente número de la lista. El
  ;;  inicio del proceso es especial, ya que entonces
  ;;  no hay resultados todavía. `reduce` tiene dos
  ;;  formas de manejar esto, dos aridades en concreto.
  ;;  Llamada con dos argumentos, usa
  ;;  los dos primeros elementos de la lista
  ;;  para la primera llamada a la función.
  ;;  Aquí está reduciendo la función `+` usando la
  ;;  versión de dos aridades de `reduce`

  (reduce + [1 1 2 3 5 8 13 21])

  ;;  El proceso entonces comienza llamando a `+`
  ;;  así

  (+ 1 1)

  ;; Dar a `reduce` tres argumentos hace que use
  ;; el segundo argumento como el "resultado" inicial.

  (reduce + 100 [1 1 2 3 5 8 13 21])

  ;; Puede que hayas notado que la función `+`
  ;; toma más (y menos) de 2 argumentos.

  (+)
  (+ 1)
  (+ 1 1)
  (+ 1 1 2 3 5 8 13 21)

  ;; `+` tomará el primer argumento, si lo hay, y
  ;; lo sumará al valor "actual" (que es cero),
  ;; luego el siguiente argumento y lo sumará al nuevo
  ;; valor actual, y así sucesivamente, hasta que
  ;; haya un resultado. Este proceso suena un poco
  ;; como si acabara de describir una reducción, ¿verdad?
  ;; De hecho lo es.

  ;; Si fuéramos a implementar la función `+`, ¿cómo
  ;; podríamos hacerlo? Podríamos comenzar implementando
  ;; algo que sume dos números juntos, luego
  ;; usarlo como función reductora con
  ;; `reduce`.
  ;; Por supuesto, ahora tenemos la tarea de sumar dos
  ;; números, sin usar la función `+` existente...
  ;; 🤔 SICP tiene esto, usando Aritmética de Peano
  ;; https://youtu.be/V_7mmwpgJHU?t=814
  ;; https://en.wikipedia.org/wiki/Peano_axioms#Addition

  (defn plus [x y]
    (if (zero? x)
      y
      (plus (dec x) (inc y))))

  ;; Pero hay demasiados casos que no se manejan aquí...
  ;; Hmm... Mantengámoslo simple y solo hagamos
  ;; matemáticas con enteros. Entonces podemos usar el método
  ;; `Integer.sum(x, y)` de Java.

  (Integer/sum 1 1)

  ;; Genial, con esto podemos crear una función `add-two`

  (defn add-two [x y]
    (Integer/sum x y))
  (add-two 1 1)

  ;; A diferencia de `+`, esta no es completamente componible
  ;; con una función de orden superior como apply

  (apply add-two [])
  (apply add-two [1])
  (apply add-two [1 1])
  (apply add-two [1 1 2 3 5 8 13 21])

  ;; Necesitamos `add-many`. Con `reduce` y nuestro
  ;; `add-two` podemos definir `add-many` así

  (defn add-many [& numbers]
    (reduce add-two numbers))
  ;; Eso lo resuelve, ¿verdad?

  (apply add-many [1])
  (apply add-many [1 1])
  (apply add-many [1 1 2 3 5 8 13 21])

  ;; ¿Qué pasa con la versión de cero aridades de `+`,
  ;; preguntas? Correcto, eso explotará

  (add-many)

  ;; La función `+` incorporada tiene un valor "actual" predeterminado
  ;; de cero, ¿recuerdas? Podemos agregar eso a
  ;; `add-many` de dos formas: O agregar una firma de cero aridades,
  ;; o usar la aridad de tres argumentos de `reduce`. Vamos
  ;; por la última opción, ya que estamos aprendiendo
  ;; sobre reduce aquí:

  (defn add* [& numbers]
    (reduce add-two 0 numbers))
  (add*)
  (add* 1)
  (add* 1 1)
  (add* 1 1 2 3)

  ;; BOOM.

  ;; También podemos usarla con `apply`:
  (apply add* [])
  (apply add* [1])
  (apply add* [1 1])
  (apply add* [1 1 2 3 5 8 13 21])

  ;; O `reduce`:
  (reduce add* [])
  (reduce add* [1])
  (reduce add* [1 1])
  (reduce add* [1 1 2 3 5 8 13 21])

  ;; Aparte de que solo manejamos enteros, nuestro `add*`
  ;; es muy parecido a cómo está implementado `+` en
  ;; Clojure core. Revísalo (en la ventana de salida):

  (source +)

  ;; Hmm, bueno, parece que usan firmas de funciones
  ;; de múltiples aridades para los casos de baja aridad, probablemente
  ;; por el casting, pero de todas formas, 😀

  ;; Hay una cosa más con `reduce` que queremos
  ;; mencionar. Al escribir funciones reductoras puedes
  ;; detener el proceso antes de que se agote la secuencia de entrada,
  ;; usando la función `reduced`. Digamos que
  ;; queremos la secuencia de entrada como una cadena separada por
  ;; `:`, como arriba, pero detenerse cuando veamos un elemento `nil`.
  ;; Aquí está la última versión para comparar:

  (reduce (fn [acc n]
            (str acc ":" n))
          [1 1 2 3 5 8 nil 13 21])

  ;; Podemos cortocircuitar el proceso llamando
  ;; a `reduced` con el valor acumulado cuando
  ;; encontramos un elemento `nil`

  (reduce (fn [acc n]
            (if (nil? n)
              (reduced acc)
              (str acc ":" n)))
          [1 1 2 3 5 8 nil 13 21])

  ;; Esto es lo que está pasando

  (doc reduced)

  ;; Reducir es un concepto muy importante en Clojure
  ;; ya que es un lenguaje "funcional primero". O como
  ;; se dice en este episodio de Functional Design
  ;; https://clojuredesign.club/episode/058-reducing-it-down/
  ;; "Las funciones reductoras son la columna vertebral de la
  ;; programación funcional, porque no tenemos mutación."

  ;; De hecho en Clojure reducir es tan importante que
  ;; Rich Hickey ha agregado una biblioteca completa con reductores
  ;; con aún más potencia
  ;; https://clojure.org/reference/reducers
  ;; De nuevo, el dúo de Functional Design, Nate Jones, y
  ;; Christoph Neumann han examinado esta biblioteca
  ;; un poco:
  ;; https://clojuredesign.club/episode/060-reduce-done-quick/
  ;; Cita asombrosa de ese episodio:
  ;;   "La abstracción seq, es bastante perezosa."

  ;; No vamos a caer en el agujero de conejo de la
  ;; biblioteca `reducers`, sin embargo...
  )

;; ... En cambio estamos recogiendo que Nate y
;; Christoph mencionan tres conceptos súper importantes
;; en esas dos citas anteriores.
;; * inmutabilidad
;; * la abstracción `seq`
;; * pereza
;; Están relacionados, y tal vez sea mejor empezar con
;; la inmutabilidad...

(comment
  ;; = Inmutabilidad =
  ;; Es bastante sorprendente que hayamos estado hablando sobre
  ;; Clojure durante tanto tiempo sin discutir cómo
  ;; nos alienta a evitar mutar nuestros datos a medida
  ;; que se procesan. Los clojuristas nunca nos callamos
  ;; sobre la inmutabilidad, ¿verdad? Casi podemos sonar
  ;; como rothbardianos al definirnos como
  ;; Enemigos del Estado 😄
  ;; https://www.youtube.com/watch?v=qe60zwUAOqE

  ;; Esto es hasta cierto punto verdad, como clojuristas
  ;; frecuentemente intentamos mantenernos en un modo de transformación de datos
  ;; durante la duración de una operación y solo tratamos
  ;; con el mundo impuro, en los "límites": al
  ;; inicio podemos estar leyendo alguna entrada, y
  ;; al final podemos estar actualizando una base de datos,
  ;; imprimiendo los resultados en un archivo (o en la
  ;; pantalla), o mutando el DOM de una página web.

  ;; Clojure nos alienta a caminar por el
  ;; camino inmutable de muchas maneras, dos de las cuales voy
  ;; a mencionar un poco aquí:
  ;; * Estructuras de Datos Persistentes
  ;; * Funciones Puras

  ;; == Estructuras de Datos Persistentes ==
  ;; Clojure nos ayuda a mantenernos en la tierra inmutable
  ;; proporcionándonos estructuras de datos inmutables.
  ;; La implementación de estas se llama Estructura de Datos Persistente:
  ;; https://en.wikipedia.org/wiki/Persistent_data_structure
  ;; En efecto significa que las estructuras de datos nunca
  ;; cambian. Las funciones que usamos para transformarlas
  ;; en realidad crean copias. (De una manera muy inteligente,
  ;; así que no empieces a preocuparte ahora.)

  ;; Digamos que definimos un vector de algunos dígitos

  (def eighteen [1 0 0 1 0])
  eighteen

  ;; Ahora queremos cambiar ese último `0` a un `1`
  ;; Podemos usar la función `assoc`. Cuando se usa en
  ;; un vector, toma un índice y el nuevo valor

  (def nineteen (assoc eighteen 4 1))
  nineteen

  ;; Examinando `eighteen` de nuevo...

  eighteen

  ;; ... vemos que sigue siendo fiel a su nombre.
  ;; Asociar un `1` en el índice 4 creó una
  ;; copia, que luego se definió como `nineteen`

  ;; Quizás obvio, esto también se cumple en los vínculos
  ;; locales.

  (let [origin {:x 0
                :y 0}
        x-travel (assoc origin :x 100)]
    [origin x-travel])

  ;; Esto proporciona un flujo de programa muy determinista.
  ;; Los datos no cambian caprichosamente bajo nuestros
  ;; pies. Y los procesos de transformación que no
  ;; mutan el estado son mucho más fáciles de paralelizar,
  ;; otros hilos no pueden cambiar los datos que estás
  ;; transformando. ¡Una categoría entera de errores nunca
  ;; tiene la oportunidad de eclosionar!

  ;; Otro beneficio que obtenemos de la inmutabilidad es
  ;; que Clojure puede ofrecer eficientemente igualdad de valores.
  ;; Los valores son inmutables, por definición.
  ;; En Clojure, incluso las estructuras de datos más profundas pueden
  ;; compararse en un instante.

  ;; Mostremos esto con una estructura no tan profunda
  ;; (excepto en el nombre)

  (def universa {:one {"Alice" {:x 100
                                :y 100
                                :z 100}
                       "Bob" {:x 100
                              :y 100
                              :z 100}}
                 :two {"Alice" {:x 100
                                :y 100
                                :z 100}
                       "Bob" {:x 100
                              :y 100
                              :z 99}}})

  (= (:one universa)
     (:two universa))

  ;; `update-in` es una función de orden superior para
  ;;  transformar estructuras de datos dada una
  ;;  "dirección" y una función. Podemos usarla
  ;;  para hacer que dos-Bob encuentre a dos-Alice, igual que
  ;;  uno-Bob y uno-Alice se han encontrado

  (def unified-universa
    (update-in universa [:two "Bob" :z] inc))
  unified-universa

  (= (:one unified-universa)
     (:two unified-universa))

  (= universa unified-universa)

  ;; ¡Nunca más tendrás que escribir un método `equals()`! 😄

  ;; La inmutabilidad también hace que nuestros programas sean diferentes
  ;; de lo que son cuando puedes cambiar el valor de
  ;; una variable a voluntad. Puede llevar un tiempo acostumbrarse
  ;; a esto. (Yo todavía estoy en el punto donde
  ;; me resulta más fácil ver soluciones mutantes a
  ;; muchos problemas. Cada vez menos, pero
  ;; de todas formas. Probablemente lo comprenderás más rápido que
  ;; yo.)

  ;; Definitivamente vale la pena insistir en comprenderlo.
  ;; La recompensa es enorme. Si solo vas a
  ;; revisar uno de los recursos que recomiendo
  ;; en esta guía, sugiero que sea este sobre
  ;; resolver problemas a la manera Clojure, de Rafal
  ;; Dittwald:
  ;; https://www.youtube.com/watch?v=vK1DazRK_a0
  ;; Spoiler: En el video no usa Clojure

  ;; Por supuesto, en la charla, Rafal no solo
  ;; finge que los datos son inmutables. También
  ;; emplea la pureza funcional.

  ;; == Funciones puras
  ;; Clojure no te fuerza a la pureza, como
  ;; algunos lenguajes hacen (mirándote a ti, Haskell), pero
  ;; hace que sea fácil caer en el hábito de
  ;; escribir funciones puras y así empujar los efectos secundarios
  ;; hacia los "bordes" de tu programa.

  ;; Una función se considera pura si cumple con
  ;; estas reglas:
  ;; 1. Siempre devuelve el mismo valor para la misma entrada
  ;; 2. No afecta nada en su entorno.
  ;;    Es decir, no muta nada, incluido no imprimir
  ;;    nada en ningún lugar, ni llamar a endpoints de API mutantes.

  ;; Una función pura es determinista y puedes
  ;; llamarla de forma segura sin preocuparte de que actualice
  ;; el estado de la aplicación o haga cualquier otra cosa que
  ;; calcular su valor de retorno basado en la entrada que
  ;; le das, y nada más que la entrada que le das.
  )

;; Antes de examinar la abstracción `seq`, desviémonos
;; un poco hacia algunas funciones comunes del núcleo de Clojure para
;; transformar estructuras de datos.

(comment
  ;; = Transformando Estructuras de Datos =
  ;; Clojure tiene una biblioteca principal que hace fácil,
  ;; divertido y legible "llegar" a una estructura de datos
  ;; y manipularla, creando una
  ;; copia con el resultado.

  ;; Hemos visto `assoc`, que crea una copia de
  ;; la estructura de datos con un nuevo valor en el índice
  ;; (en el caso de un `vector`) o clave (en el caso de un mapa)

  (def colt-express
    {:name "Colt Express"
     :categories ["Family"
                  "Strategy"]
     :play-time 40
     :ratings {:pez 5
               :kat 5
               :wiv 5
               :vig 3
               :rex 5
               :lun 4}})

  (def exit-haunted
    {:name "EXIT: The Haunted Roller Coaster"
     :categories ["Family"
                  "Co-op"
                  "Puzzle"
                  "Cards"]
     :ratings {:pez 5
               :kat 5
               :wiv 5
               :vig 4
               :rex 5}})

  ;; `assoc` puede agregar una nueva clave a un mapa

  (def colt-express-w-age
    (assoc colt-express :age-from 10))

  ;; Con un vector solo puedes agregar un nuevo elemento justo
  ;; después del último elemento, no más allá

  (def board-games-empty
    [])

  (def board-games-w-c-e
    (assoc board-games-empty 0 colt-express))

  ;; board-games-empty todavía está vacío. Por lo tanto

  (def board-games-w-c-e-and-exit-fail
    (assoc board-games-empty 1 exit-haunted))

  (def board-games-w-c-e-and-exit
    (assoc board-games-w-c-e 1 exit-haunted))

  ;; No es que sea muy común agregar cosas
  ;; a un vector usando `assoc`. Para esto `conj`
  ;; frecuentemente tiene más sentido

  (conj board-games-empty colt-express exit-haunted)

  ;; `assoc` en mapas puede reemplazar valores existentes
  ;; (en la copia)

  (def colt-express-w-age-and-adjusted-playtime
    (assoc colt-express-w-age :play-time 45))

  ;; `assoc` en vectores también puede hacer esto

  (def board-games-w-adjusted-c-e
    (assoc board-games-w-c-e
           0
           colt-express-w-age-and-adjusted-playtime))

  ;; Puedes `assoc` múltiples cosas en una llamada

  (assoc colt-express
         :play-time 50
         :age-from 10)

  (assoc board-games-empty
         0 colt-express
         1 exit-haunted)

  ;; (De nuevo, está `conj` para esto.)

  ;; Con los mapas también hay `merge`, que te permite
  ;; fusionar dos o más mapas juntos

  (merge colt-express
         {:play-time 45
          :age-from 10})

  ;; NB: Es una fusión "superficial", así que agregar una calificación de un
  ;; miembro de la familia así no funcionará.

  (merge exit-haunted
         {:play-time 90
          :ratings {:lun 5}
          :age-from 10})

  ;; `assoc` hace lo mismo

  (assoc exit-haunted :ratings {:lun 5})

  ;; No hay deep-merge en Clojure core, pero
  ;; está `assoc-in` para llegar más profundo
  ;; En lugar de una clave (o índice) toma una "ruta"

  (assoc-in exit-haunted [:ratings :lun] 5)

  (assoc-in colt-express [:categories 2] "Planning")
  ;; (Pero... no lo hagas, ver abajo bajo `update` para
  ;; cómo hacer `conj` de la categoría en cambio.)

  ;; A diferencia de `assoc`, solo puedes agregar una cosa
  ;; a la vez con `assoc-in`

  ;; Eliminar cosas de un mapa se hace con
  ;; `dissoc`

  (dissoc colt-express :play-time :ratings :categories)

  ;; Probablemente usarás `dissoc` con frecuencia con
  ;; el REPL (como lo haces en este archivo) para
  ;; examinar algunas estructuras de datos que podrían
  ;; tener algunas estructuras de datos grandes en ellas,
  ;; como un log o algo así

  (dissoc colt-express :log)
  ;; (Esta estructura de datos no tenía ningún log,
  ;; así que no se modificó, pero de todas formas.)

  ;; No hay `dissoc-in` en Clojure core, pero
  ;; regresemos a eso después de haber visitado
  ;; `update` y `update-in`.

  ;; `update` y `update-in` son similares a
  ;; sus equivalentes `assoc`, pero en lugar de un
  ;; valor, toman una función que se usa para
  ;; manipular el valor.

  (update exit-haunted :name string/upper-case)

  ;; Un ejercicio para ti: Actualiza el `:play-time`
  ;; de la entrada `colt-express` con 5 o algo así




  ;; Los argumentos que agregas después de la función
  ;; se pasan a la función

  (update colt-express :categories conj "Planning")

  ;; Ejercicio: Haz que tu actualización del :play-time
  ;; tome el `5` (o algo) como argumento.




  ;; Ejercicio: Elimina las entradas `:pez` y `:wiv`
  ;; de los `:ratings` de `exit-haunted`




  ;; `update-in` es a `assoc-in` lo que `update` es
  ;; a `assoc`.

  (update-in colt-express [:ratings :lun] inc)

  (update-in colt-express [:ratings :lun] + 9000)
  ;; https://www.youtube.com/watch?v=PCHxU7witPA

  ;; Ejercicio: No hay `dissoc-in`, pero parece
  ;; que puedes usar `update-in` para esto,
  ;; ¿verdad?




  ;; La recompensa es una visita menos a StackOverflow
  ;; cuando te falta `dissoc-in` 😄
  ;; https://stackoverflow.com/a/21942548/44639

  ;; Hemos usado los keywords como funciones de búsqueda en mapas
  ;; antes. Está bien, pero a veces puedes
  ;; preferir la función `get`

  (get colt-express :ratings)

  (= (:ratings colt-express)
     (get colt-express :ratings))

  ;; `get` toma un tercer argumento que se usará
  ;;  como predeterminado, si falta la entrada

  (get exit-haunted :play-time 0)

  ;; Los keywords como funciones de búsqueda también admiten
  ;; esto

  (:play-time exit-haunted 0)

  ;; Sin el predeterminado, se devolverá `nil`.
  ;; Lo que podría explotar, dependiendo de para qué
  ;; uses el valor

  (* (get colt-express :play-time) 2)
  (* (get exit-haunted :play-time) 2)

  ;; Mejor prevenir que curar, en casos como este

  (* (get colt-express :play-time 0) 2)
  (* (get exit-haunted :play-time 0) 2)

  ;; Sí, también hay `get-in`
  ;; Ejercicio: Usa `get-in` para obtener mi calificación
  ;; en estos dos maravillosos juegos de familia


  ;; Puede que hayas notado que todas las
  ;; funciones en esta sección toman la colección
  ;; como su primer argumento. Eso las hace
  ;; fáciles de usar con la macro Thread First, `->`.
  ;; Esto es por diseño y muy idiomático en
  ;; Clojure.

  ;; Es común ver tuberías de transformación de datos
  ;; como esta

  (-> exit-haunted
      (assoc :play-time 90)
      (update :categories conj "Scary")
      (assoc-in [:ratings :lun] 5)
      (update-in [:ratings :vig] + 1)
      (dissoc :name)
      (update :log vec)
      (update :log conj "Name redacted")
      (update :log conj "(Because scary)"))

  ;; (Aunque quizás más significativa que esa)

  ;; Puedo recomendar navegar por "Ver también" en ClojureDocs
  ;; comenzando aquí:
  ;; https://clojuredocs.org/clojure.core/update-in
  ;; Y pegando muchos ejemplos aquí para
  ;; experimentar.
  )

(comment
  ;; == Manipulando `sets` ==
  ;; Los mapas, vectores y conjuntos son el pan y la
  ;; mantequilla de la mayoría de los programas Clojure. Con la
  ;; increíble sintaxis literal para estos, el código se vuelve
  ;; fácil de leer y razonar. Y
  ;; manipularlos es fácil e intuitivo.

  ;; Los `sets` son `seqs` (más sobre eso después)
)


;; Continuará...

;; Hasta que haya más material que leer aquí, quizás
;; es momento de que veas cómo conectar Calva a
;; tus proyectos Clojure/ClojureScript:
;; https://calva.io/connect/

;; Cosas en la lista de temas por escribir:
;; metadatos
;; comentarios
;; desestructuración
;; átomos
;; nil, seguridad con nil, nil punning
;; seqs
;; pereza
;; loop, recur
;; depuración
;; algunos ejercicios de repaso aquí y allá

;; Aprende mucho más sobre Clojure en https://clojure.org/
;; También está ClojureScript, el mismo maravilloso lenguaje,
;; para VMs JavaScript: https://clojurescript.org

;; Hay tanto sobre Clojure que no se menciona en esta
;; guía corta. https://clojure.org/ es donde
;; vas para la historia completa.

;; Para obtener ayuda con tus preguntas sobre Clojure, revisa estos
;; recursos:
;; https://ask.clojure.org/
;; https://clojurians.net
;; https://clojureverse.org
;; https://www.reddit.com/r/Clojure/
;; https://exercism.io/tracks/clojure

;; Y también hay muchos otros recursos, como:
;; https://clojuredocs.org
;; https://clojure.org/api/cheatsheet

"Archivo cargado. ¡Bienvenido a Clojure! ♥️"

;; Esta guía fue descargada de:
;; https://github.com/BetterThanTomorrow/dram
;; Por favor, considera contribuir.

(ns get-started.hello-paredit)

;; Comienza cargando este archivo
;; Ctrl+Alt+C Enter

;; Clojure es un LISP y por lo tanto el código
;; es estructural. Todo está organizado en
;; "formas", también conocidas como S-expresiones (sexprs).
;; https://en.wikipedia.org/wiki/S-expression
;; Una forma es cualquier literal o "símbolo" o
;; colección literal (diferentes tipos de
;; listas) de literales. Paredit te ayuda a aprovechar
;; esta estructura.

;; == Hay Muchos Comandos de Paredit ==
;; Busca en la paleta de comandos de VS Code
;; "Paredit" para ver todos sus comandos. Presta
;; mucha atención a los atajos que muestra
;; para los comandos que uses con frecuencia.
;; Consulta https://calva.io/paredit para mucho
;; más de lo que mostramos aquí.

;; Evalúa esto con Alt+Enter

(->> ["I" "💖" "Paredit"]
     (interpose " ~ ")
     (apply str))

;; (Para entrar en buen ánimo. 😍)

;; == Protección del Modo Estricto ==
;; El modo estricto de Paredit está activo por defecto.
;; Te ayudará a no eliminar corchetes que
;; romperían la estructura del código.
;; Usa Alt+Backspace para anularlo.

(defn strict-greet
  "Try to remove brackets and string quotes
   using Backspace or Delete. Try the same
   with the Alt key pressed."
  [name]
  (str "Strictly yours, " name "!"))

;; (Restaura con *Undo* si es necesario.)

;; == Selecciona con Facilidad ==
;; Coloca el cursor en una forma, por ejemplo en `select-me`
;; a continuación, y luego usa *Paredit Expand Selection*
;; Repite el comando para expandir un nivel más

(comment
  (-> 4
      (repeat (let [select-me 'bar]
                {:foo select-me}))
      (->>
       (repeat 3))
      (vec)))

;; También existe *Paredit Shrink Selection*

;; == Navega la Estructura ==
;; Muévete forma por forma usando *Paredit Forward Sexp*
;; y *Paredit Backward Sexp*
;; Nota: A pesar de lo que muestra la paleta de comandos, los
;; atajos de teclado para Mac son alt+right/left y
;; para Windows y Linux son ctrl+right/left
;; Consulta https://github.com/BetterThanTomorrow/calva/issues/1161

(def move
  [{:zero 0}
   1 2 3
   "four"
   #:five {:bar 'baz}])

;; También prueba *Paredit Select Forward/Backward*
;; Todos los comandos *Paredit Select ...* funcionan junto con
;; *Paredit Expand/Shrink Selection*

;; == Edita la Estructura ==
;; Una eliminación estructural al día mantiene al médico alejado
;; Busca en la Paleta de Comandos *Paredit Kill*

(defn delete
  "Strings are treated a bit like lists, try
   *Kill/Delete Sexp Backwards* and others in this
   documentation string"
  [kill-forward kill-backward]
  [{:zero 0}
   1 2 3
   "four"
   #:five {:foo kill-forward
           :bar kill-backward}]
  "To delete and copy, use *Paredit Select ...*
   then *Cut*")

;; === Mueve esos Corchetes ===
;; *Paredit Slurp* y *Paredit Barf* son comandos
;; útiles para mover formas hacia adentro y afuera de la
;; lista/vector/mapa/cadena actual (o mover corchetes, dependiendo
;; de tu perspectiva.)

(def slurp-barf [{:barf-me "barf-me-too"}
                 'slurp-me-then-barf-me])

;; === Elevar ===
;; *Paredit Raise Sexp* reemplaza la forma envolvente
;; con la forma "actual"
;; Eleva y deshace un poco en este fragmento:

(comment
  (def raise-me
    #:or-raise-me {:or-me [1 2 3 4]
                   :or-this-> #{1 2 3 4}}))

;; Aprende mucho más sobre Paredit: https://calva.io/paredit

;; == Inserta Moneda para Continuar ==
;; Si eres nuevo en Clojure, por favor continúa
;; con `hello_clojure.clj` y evalúa tu camino
;; hacia algunos conocimientos básicos de Clojure.

"Hola Calva Paredit λ 💖 🚀"

;; Esta guía fue descargada de:
;; https://github.com/BetterThanTomorrow/dram
;; Por favor, considera contribuir.

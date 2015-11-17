
##Comment créer une nouvelle page:

###1) Créer un nouvel objet java

```java
public class MyView extends MybaLayout
{
 	public MyView()
	{
        super("XXX", showIcon, showOverlay, isDocked);
    }
}
``` 

###2) Créer le fichier de layout

- Il faut créer un nouveau ficher HTML dans le dossier

```shell
WEB/VAADIN/themes/myba/layouts/xxx.html
```

- Il faut impérativement que ce fichier commence avec cette balise, cette balise ne peux pas être contenue dans une autre balise

```html
<div location="header"></div>
```

###3)Donner un style a son layout

- Dans le fichier html du layout il suffit d'utiliser la notation classique pour appliquer une classe ou un id a un élément

```html
<div location="logo" class="logo"></div>
```

- Ensuite il faut ajouter au fichier:

```shell
WEB/VAADIN/themes/myba/styles.scss
```

```scss
@import "style/myLayout.scss";
.
.
.
.myba
{
   @include myLayout;
}
```

- Enfin il faut créer un fichier "myLayout.scss" ( le même nom que @import juste au dessus en gros) dans le dossier

```
WEB/VAADIN/themes/myba/style/
```

- Et lui ajouter un mixin comme suit, le nom du mixin doit etre le même que le @include d'avant

```scss
@mixin myLayout
{
    .logo
    {
        /*CODE CSS*/
    }
}
```

###4) Ajouter cette page a la liste des pages disponibles:

-  Il faut ajouter une ligne au fichier "MybaUI.java"

```java
@Theme("myba")
public class MybaUI extends UI
{
    @Override
    protected void init(VaadinRequest vaadinRequest)
    {
        *
        *
        *

        navigator.addView("LE_NOM_DE_LA_VUE", new MyView());
    }
}
```

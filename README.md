## Mitä tarkoittaa navigointi Jetpack Composessa. 

#### Voi navigoida eri näkymistä toisiin ja tietää missä näkymässä olet
        
## Mitä ovat NavHost ja NavController. 

#### NavHost on kontti joka näyttää nykyisen ruudun. NavController on ohjain navigaatiolle, sillä voi liikkua eri näkymien välillä.
        
## Miten sovelluksesi navigaatiorakenne on toteutettu (Home ↔ Calendar).

#### Navigaatio hoituu MainActivity:ssa käyttäen NavController ja NavHost, home ja calender on määriteltyjä reittejä ja niille suunnnatut napit vaihtavat näkymää

## Miten MVVM ja navigointi yhdistyvät (yksi ViewModel kahdelle screenille).

#### MVVM ja navigointi yhdistyvät luomalla yksi TasKViewModel aktiiviteetin tasolle ja välittämällä se Homescreen ja CalendarScreen tiedostoihin jotta ne jakavat saman tilan

## Miten ViewModelin tila jaetaan kummankin ruudun välillä.

#### Kumpikin ruutu hakee listan samasta StateFlow:sta ViewModeli:ssa 

## Miten CalendarScreen on toteutettu (miten tehtävät ryhmitellään / esitetään kalenterimaisesti).

#### CalendarScreen on toteutettu ryhmittämällä tehtävät dueDate:n mukaan ja se näyttää ne tehtävät päivämäärien alla 

## Miten AlertDialog hoitaa addTask ja editTask.

#### AddTask:ssa Add-painikkeen painaminen avaa AlertDialogin tyhjillä syötekentillä, ja Save luo uuden tehtävän kutsumalla vm.addTask(...).
#### EditTask:n olemassa olevan tehtävän napauttaminen avaa AlertDialogin, jossa on jo aiemmin tallennetut tiedot, Save kutsuu updateTask ja Delete kutsuu removeTask


-------------------------------------------------------------------------------------------
old:

    # Selitä, miten Compose-tilanhallinta toimii.
    ### UI päivittyy automaattisesti eikä sitä tarvitse erikseen päivittää

    # Kerro, miksi ViewModel on parempi kuin pelkkä remember.
    ### ViewModel data säilyy tilanteissa, joissa se katoaisi jos käyttää "remember" 


    # Selitä MVVM, miksi se on hyödyllinen Compose-sovelluksissa.
    ### se erottaa käyttöliittymän ja logiikan 
    
    # Kerro miten StateFlow toimii.
    ### UI kuuntelee stateflowta koko ajan, ja jos stateflow muuttuu niin UI muuttuu sen mukaan



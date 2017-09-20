 # Page Notifier
 Cz�sto czekaj�c na konkretn� informacj� (w moim przypadku by�y to wyniki kolokwium, czy egzaminu) na konkretnej stronie, sprawdza�em j� ca�kiem sporo razy na dzie�. Proces - odpalanie przegl�darki i szukanie na stronie informacji wyda� mi si� m�cz�cy (gdy robi si� to �rednio co 10 minut). Zainstalowa�em wi�c wtyczk� do Firefoxa informuj�c� o zmianach na stronie. Dzia�a�a ona jednak tylko, gdy w��czony by� Firefox. Potrzebuj� wi�c wygodniejszego sposobu na elastyczne, szybkie i wygodne powiadamianie.
 
 # aplikacja
 Implementacja "sprawdzacza" w systemie Android. Aplikacja, kt�ra po wprowadzeniu szczeg��w, b�dzie na bie��co, w tle, sprawdza� konkretn� stron� w poszukiwaniu zmian od chwili wydania polecenia obserwacji.

 # screenshoty (wersja beta 0.8)
 
<div align="center">
   
	<img src="screenshots/main_ss.png" width="25%" />
   
	<img src="screenshots/details_ss.png" width="25%" />
   
	<img src="screenshots/newtask_ss.png" width="25%" />
  
	<img src="screenshots/settings_ss.png" width="25%" />

</div>

 # czego si� nauczy�em:
* Systematycznego, etapowego i rozmy�lnego podej�cia do projektu - najpierw layout, potem kod UI (szkielet dzia�ania), a na koniec implementacja
* Korzystania z ContentProviders (wygodny dost�p do bazy danych z ka�dego miejsca w projekcie
* Korzystania z serwis�w i zasad ich dzia�ania (najpierw pr�bowa�em IntentService, potem okaza�o si�, �e JobScheduler pasuje lepiej do mojego typu zadania)
* Improvement w projektowaniu architektury - starania w celu trzymania si� zasad rozdzielenia abstrakcji (jest sporo do poprawy, ale jest ju� zdecydowanie lepiej ni� kiedy�)
* Pisania lepszego kodu (zasada pojedynczej odpowiedzialno�ci, opisowe nazwy zmiennych i funkcji, usystematyzowanie !jednolitego! stylu !W KO�CU!)

 # czego musz� si� jeszcze nauczy�:
* Testy - mockowanie, Espresso, JUnit, bo wci�� nie ma u mnie kultury testera: "najpierw testy - potem implementacja"
* Wielow�tkowo�� - uda�o si� j� osi�gn��, ale to by� ten pierwszy raz i nast�pny musi by� lepszy!


 # TODO
 * ~~Zacz��~~ (Done!)
 * ~~Layout aplikacji~~ (Done!)
 * ~~Implementacja logiki UI reaguj�cej na serwis~~ (Done!)
 * ~~Opcja dodawania item�w z adresami stron do bazy i przegl�dania ich z mo�liwo�ci� edycji i usuwania~~ (Done!)
 * ~~Opcja uruchomienia us�ugi por�wnuj�cej stron� z dan� przez u�ytkownika cz�stotliwo�ci� (dzia�aj�cej w tle niezale�nie od aktywno�ci g��wnej~~ (Done!)
 * ~~Opcja wys�ania powiadomienia przez us�ug� dzia�aj�c� w tle~~ (Done!)
 * ~~Refactor metod do uruchamiania serwisu w MainActivity (mo�e oddzielna klasa?)~~ (Done!)
 * ~~Logo aplikacji i t�umaczenie string�w na j�zyk angielski~~ (Done!)
 * �ledzenie pobranej ilo�ci danych
 * Uruchamianie zadania w zale�no�ci od dost�pno�ci WiFi lub sieci
 * Porz�dne czyszczenie kodu - usuni�cie log�w do debuggowania
 * Testy testy testy i jeszcze raz testy!



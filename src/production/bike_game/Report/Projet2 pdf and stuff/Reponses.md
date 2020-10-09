##Question 1:
Un objet de type *Window* est plus exposé que *Canvas* ou *Keyboard* (il est inutile d'exposer inutilement des fonctionnalités telles que dispose ou update uniquement pour accéder au canevas de dessin ou au gestionnaire de clavier)

##Question 2:
Cela implique de devoir enrichir l'API de *ActorGame* pour offrir des fonctionnalités plus spécifiques et moins intrusives qu'offrir l'accès au moteur physique. Par exemple pour notre cas, ajouter une méthode créant une *Entity* (*ActorGame* sait créer une *Entity* car il a un accès direct à *World*, il peut donc  offrir ce service de création d'une *Entity* sans dévoiler *World*).
L'accès au *World* est intrusif car il permet à quiconque d'agir sur le moteur physique (il devient par exemple possible à une *GameEntity* de modifier la gravité du monde ce qui n'est pas forcément très naturel)

##Question 3:
*ActorGame* est un objet partagé dans tous le moteur de jeu (on veut que les *GameEntity*/*Actor* qui y évoluent fasse partie du même ActorGame). Faire une copie défensive voudrait dire dupliquer l'intégralité du jeu, avec toute sa liste d'acteurs et son moteur physique à chaque fois que l'on crée une *GameEntity*, ce qui n'as pas de sens). Les copies défensives doivent avoir lieu si l'on se souhaite pas partager les objets passés en paramètre. 
Dans le cadre du projet, nous comptons plutôt sur les abstractions fournies par les interfaces (*Game* et *Actor*) pour nous protéger des failles d'encapslulation)

##Question 4:
*protected* a l'avantage de restreindre l'accès aux *Entity* du moteur physique aux programmeurs d'extension du moteur de jeu. Cela va néamnoins compliquer certains accès dans les sous-paquetages (si un programmeur d'extension décide d'ajouter un sous-paquetage du paquetage actor, *getEntity* n'y est plus accessible) (voir les questions 5 et 6 auxquelles i  vous appartient de trouver les bonnes réponses)

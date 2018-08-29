all: help

compile:
	@cd test && \
	javac -cp ".:libs/*:libs/test-libs/*" Teste.java

run:
	@clear
	@cd test && \
	chmod +x geckodriver && \
	java -cp ".:libs/*:libs/test-libs/*" Teste

server: @kill-server
	php -S localhost:9990 -t app/

happy: @hidden-server compile run
	@echo ""
	@make @kill-server

zip:
	@while [ -z "$$STUDENT" ]; do \
		read -r -p "Digite seu nome, removendo acentos e trocando os espaços por traços (-): " STUDENT; \
	done ; \
	zip -r $$STUDENT.zip app



@kill-server:
	@if [ -f server.PID ] ; then \
		kill `cat server.PID` || true; \
	fi;
	@rm server.PID || true
	# @kill `ps aux | grep php.*app/ | awk '{print $2}' | head -1` || true
	# @rm server.PID || true

@hidden-server: @kill-server
	@rm server.log || true
	@php -S localhost:9990 -t app/ > server.log 2>&1 & echo $$! > server.PID

@hidden-server_dir: @kill-server
	@rm server.log || true
	@php -S localhost:9990 -t answers/_/app/ > server.log 2>&1 & echo $$! > server.PID

@task:
	@rm -rf $(name)
	@mkdir -p $(name)/app
	@cp -R app/_base/ $(name)/app/
	@mkdir -p $(name)/test/libs
	@cp test/*.java $(name)/test/
	@cp Makefile $(name)/
	@zip -r $(name).zip $(name)/
	@rm -rf $(name)

@prepare:
	@mkdir -p app
	@mkdir -p test
	@cd test && ln -s ~/Dropbox/IFPE/test-libs/* .



@correct: @hidden-server_dir compile run
	@echo ""
	@make @kill-server



help:
	@clear
	@echo "+----------------------------------------------------------------------------+"
	@echo "| Ajuda                                                                      |"
	@echo "+----------------------------------------------------------------------------+"
	@echo "| compile:  compila as classes de teste em Java dentro da pasta 'test'       |"
	@echo "| run:      roda os testes e mostra a(s) nota(s) ao final                    |"
	@echo "| server:   inicia o servidor, setando o root para o diretório 'app'         |"
	@echo "| happy:    compila e executa os testes, sem mais preocupações               |"
	@echo "| zip:      compacta o diretório 'app' dentro de um arquivo com o seu nome   |"
	@echo "+----------------------------------------------------------------------------+"

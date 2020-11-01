package br.newtonpaiva.lab.tcc.domain.service

import spock.lang.Specification

class CursoServiceImplSpec extends Specification {

    def repo = Mock(CursoRepository.class)

    def "run" () {
        given:
        def test = 'a'

        when:
        def b = test

        then:
        b == a
    }
}
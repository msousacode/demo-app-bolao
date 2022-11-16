package com.msousacode.bolao.web.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/campeonatos")
public class CampeonatoController {

   /* @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Transactional
    @PostMapping
    public ResponseEntity<CampeonatoDTO> cadastrar(@RequestBody @Valid CampeonatoDTO campeonatoDTO) {

        var campeonato = new Campeonato();
        BeanUtils.copyProperties(campeonatoDTO, campeonato);

        var result = campeonatoRepository.save(campeonato);

        var response = new CampeonatoDTO(result.getId(), result.getNome(), result.getRodada(), result.getDataInicio(), List.of());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{campeonato_id}/partidas")
    public ResponseEntity<CampeonatoDTO> buscar(
            @PathVariable("campeonato_id") @NotEmpty(message = "ID do Campeonato deve ser informado") @Valid UUID uuid) {

        var campeonato = campeonatoRepository.findById(uuid);

        if (campeonato.isPresent())
            return ResponseEntity.ok(new CampeonatoDTO(campeonato.get()));
        else
            throw new ResourceNotfoundException("Recuroso não encontrato");//TODO centralizar as mensagens de sistema
    }*/
}

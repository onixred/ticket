package my.pet.ticket.adapter;

import org.modelmapper.ModelMapper;

public abstract class AbstractMapper {

    private final ModelMapper modelMapper;

    protected AbstractMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    
    public <S, T> T map(S source, Class<T> target) {
        return modelMapper.map(source, target);
    }

}
